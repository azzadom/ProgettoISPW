package dao.jdbc.queries;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TicketQueries {

    public static ResultSet selectTicketsByEvent(Statement stmt, Integer idEvent) throws SQLException {
        String query = String.format("SELECT * FROM Ticket WHERE Event = %d;", idEvent);
        return stmt.executeQuery(query);
    }
}
