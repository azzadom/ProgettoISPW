package dao;

import exception.dao.DAOException;
import model.Ticket;

import java.util.List;

public interface TicketDAO {

    public List<Ticket> selectTickets(Integer idEvent) throws DAOException;
}
