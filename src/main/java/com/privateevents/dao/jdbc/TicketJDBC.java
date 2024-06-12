package com.privateevents.dao.jdbc;

import com.privateevents.dao.jdbc.queries.TicketQueries;
import com.privateevents.dao.TicketDAO;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Ticket;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketJDBC implements TicketDAO {

    private static final String COLUMN_TYPE = "TypeName";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_MINIMUM_AGE = "MinimumAge";
    private static final String COLUMN_DESC = "Desc";
    private static final String COLUMN_MAX_TICKETS = "MaxTickets";

    @Override
    public List<Ticket> selectTickets(Integer idEvent) throws DAOException {

        List<Ticket> tickets = new ArrayList<>();
        try (Statement stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            ResultSet rs = TicketQueries.selectTicketsByEvent(stmt, idEvent);
            while (rs.next()) {
                Ticket ticket = fromResultSet(rs);
                tickets.add(ticket);
            }
            rs.close();
            return tickets;
        } catch (SQLException e) {
            throw new DAOException("Error in selectTickets: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    private Ticket fromResultSet(ResultSet rs) throws SQLException {
        return new Ticket(rs.getString(COLUMN_TYPE), rs.getDouble(COLUMN_PRICE),
                rs.getInt(COLUMN_MINIMUM_AGE), rs.getString(COLUMN_DESC), rs.getInt(COLUMN_MAX_TICKETS));
    }
}
