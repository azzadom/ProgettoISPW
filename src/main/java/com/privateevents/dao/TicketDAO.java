package com.privateevents.dao;

import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Ticket;

import java.util.List;

public interface TicketDAO {

    public List<Ticket> selectTickets(Integer idEvent) throws DAOException;
}
