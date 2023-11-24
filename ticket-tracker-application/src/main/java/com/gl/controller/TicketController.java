package com.gl.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.model.Ticket;
import com.gl.service.TicketService;

@Controller
@RequestMapping("/admin")
public class TicketController {

	@Autowired
	TicketService service;

	@GetMapping("/tickets")
	public String getAll(Model m) {

		m.addAttribute("ticket", service.getAllTickets());
		return "home";

	}

	@GetMapping("/tickets/newTicket")
	public String addTicket(Model m) {
		Ticket ticket = new Ticket();
		ticket.setTicketCreatedOn(new Date(System.currentTimeMillis()));
		m.addAttribute("ticket", ticket);
		return "ticket-form";
	}

	@RequestMapping("/{id}/edit")
	public String edit(@PathVariable int id, Model m) {
		m.addAttribute("ticket", service.findById(id));
		return "edit-ticket";

	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable int id, Model m) {
		m.addAttribute("ticket", service.delete(id));
		return "redirect:/admin/tickets";

	}

	@PostMapping("/saveTicket")
	public String save(@ModelAttribute("ticket") Ticket ticket) {
		try {
			service.addTickect(ticket);
			return "redirect:/admin/tickets";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

	@GetMapping("/{id}/getbyId")
	public String findById(@PathVariable int id, Model m) {
		Ticket ticket = service.findById(id);

		if (ticket != null) {
			m.addAttribute("ticket", ticket);
			return "view";
		} else {
			m.addAttribute("errorMessage", "No record found for ID: " + id);
			return "error";
		}
	}

	@GetMapping("/tickets/search")
	public String searchByTitle(@RequestParam String ticketTitleOrDesc, Model model) {
		// Trim the search term and check if it's not empty
		String trimmedTitle = ticketTitleOrDesc.trim();
		if (trimmedTitle.isEmpty()) {
			model.addAttribute("errorMessage", "Invalid search term");
			return "error";
		}

		List<Ticket> searchResults = service.findByTitleOrDesc(trimmedTitle);

		if (!searchResults.isEmpty()) {
			model.addAttribute("ticket", searchResults);
			return "search-results";
		} else {
			model.addAttribute("errorMessage", "No records found for the title: " + trimmedTitle);
			return "error";
		}
	}

}
