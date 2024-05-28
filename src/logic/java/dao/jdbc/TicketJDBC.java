package dao.jdbc;

import dao.TicketDAO;
import dao.jdbc.queries.TicketQueries;
import exception.dao.DAOException;
import model.Ticket;

import static exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketJDBC implements TicketDAO {
    @Override
    public List<Ticket> selectTickets(Integer idEvent) throws DAOException {
        Statement stmt;
        List<Ticket> tickets = new ArrayList<>();
        try {

            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = TicketQueries.selectTicketsByEvent(stmt, idEvent);
            while (rs.next()) {
                Ticket ticket = new Ticket(rs.getString("TypeName"), rs.getDouble("Price"),
                        rs.getInt("MinimumAge"), rs.getString("Desc"), rs.getInt("MaxTickets"));
                tickets.add(ticket);
            }
            rs.close();
            stmt.close();
            return tickets;
        } catch (SQLException e) {
            throw new DAOException("Error in selectTickets: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }
}
