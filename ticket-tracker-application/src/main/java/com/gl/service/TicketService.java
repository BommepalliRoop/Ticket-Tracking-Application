package com.gl.service;

import java.util.List;

import com.gl.model.Ticket;

public interface TicketService {

	Ticket addTickect(Ticket t);

	Ticket findById(int id);

	String delete(int id);

	List<Ticket> getAllTickets();

	// Ticket findByTicketTitle(String ticketTitle);
	List<Ticket> findByTitleOrDesc(String ticketTitleOrTicketSDesc);
}