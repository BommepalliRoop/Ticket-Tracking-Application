package com.gl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.model.Ticket;
import com.gl.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository dao;

	@Override
	public Ticket addTickect(Ticket t) {

		return dao.save(t);

	}

	@Override
	public Ticket findById(int id) {
		Optional<Ticket> t = dao.findById(id);

		if (t.isPresent()) {
			Ticket currentTicket = t.get();
//			currentTicket.setTicketTitle(ticket.getTicketTitle());
//	        currentTicket.setTicketShortDescription(ticket.getTicketShortDescription());
			return dao.save(currentTicket);
		} else
			return null;

	}

	@Override
	public String delete(int id) {
		dao.deleteById(id);
		return "deleted successfully";

	}

	@Override
	public List<Ticket> getAllTickets() {
		// List<Ticket> list = dao.findAll();
		return dao.findAll();

	}

//	@Override
//	public Ticket findByTicketTitle(String ticketTitle) {
//		
//		return dao.;
//	}

	@Override
	public List<Ticket> findByTitleOrDesc(String ticketTitleOrDesc) {

		return dao.findByTicketTitleContainingOrTicketShortDescriptionContaining(ticketTitleOrDesc, ticketTitleOrDesc);
	}

}
