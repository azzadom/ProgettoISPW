package dao.jdbc;

import dao.EventDAO;
import dao.jdbc.queries.EventQueries;
import exception.dao.DAOException;
import model.Event;

import static exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class EventJDBC implements EventDAO {

    @Override
    public List<Event> selectEventsByCity(String city) throws DAOException {

        Statement stmt;
        List<Event> events = new ArrayList<>();
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = EventQueries.selectEventsByCity(stmt, city);
            while (rs.next()) {
                Event event = new Event(rs.getInt("IdEvent"), rs.getString("Name"), rs.getString("Desc"), rs.getString("Location"),
                        rs.getString("Address"), rs.getString("City"), rs.getDate("Date").toLocalDate(),
                        rs.getTime("Time").toLocalTime(), rs.getBoolean("BookingClosed"), rs.getString("Organizer"));
                events.add(event);
            }
            rs.close();
            stmt.close();
            return events;
        } catch (SQLException e) {
            throw new DAOException("Error in selectEventsByCity: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    public Event selectEvent(Integer idEvent) throws DAOException{
        Statement stmt;
        Event event = null;
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = EventQueries.selectEvent(stmt, idEvent);
            if (rs.next()) {
                event = new Event(rs.getInt("IdEvent"), rs.getString("Name"), rs.getString("Desc"), rs.getString("Location"),
                        rs.getString("Address"), rs.getString("City"), rs.getDate("Date").toLocalDate(),
                        rs.getTime("Time").toLocalTime(), rs.getBoolean("BookingClosed"), rs.getString("Organizer"));
            }
            rs.close();
            stmt.close();
            return event;
        } catch (SQLException e) {
            throw new DAOException("Error in selectEvent: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public List<Event> selectEventsByOrganizer(String idOrganizer) throws DAOException {

        Statement stmt;
        List<Event> events = new ArrayList<>();
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = EventQueries.selectEventsByOrganizer(stmt, idOrganizer);
            while (rs.next()) {
                Event event = new Event(rs.getInt("IdEvent"), rs.getString("Name"), rs.getString("Desc"), rs.getString("Location"),
                        rs.getString("Address"), rs.getString("City"), rs.getDate("Date").toLocalDate(),
                        rs.getTime("Time").toLocalTime(), rs.getBoolean("BookingClosed"), rs.getString("Organizer"));
                events.add(event);
            }
            rs.close();
            stmt.close();
            return events;
        } catch (SQLException e) {
            throw new DAOException("Error in selectEventsByOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }
}
