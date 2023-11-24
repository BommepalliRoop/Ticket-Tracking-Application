package com.gl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
	List<Ticket> findByTicketTitleContainingOrTicketShortDescriptionContaining(String ticketTitle,
			String ticketShortDescription);

}
