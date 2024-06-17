package com.privateevents.dao.jdbc;

import com.privateevents.dao.EventDAO;
import com.privateevents.dao.jdbc.queries.EventQueries;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Event;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

public class EventJDBC implements EventDAO {

    private static final String COLUMN_IDEVENT = "IdEvent";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_DESC = "Desc";
    private static final String COLUMN_LOCATION = "Location";
    private static final String COLUMN_ADDRESS = "Address";
    private static final String COLUMN_CITY = "City";
    private static final String COLUMN_DATE = "Date";
    private static final String COLUMN_TIME = "Time";
    private static final String COLUMN_BOOKINGCLOSED = "BookingClosed";
    private static final String COLUMN_ORGANIZER = "Organizer";

    @Override
    public List<Event> selectEventsByCity(String city) throws DAOException {
        Statement stmt;
        List<Event> events = new ArrayList<>();
        try {
            stmt = SingletonConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = EventQueries.selectEventsByCity(stmt, city);
            while (rs.next()) {
                Event event = fromResultSet(rs);
                events.add(event);
            }
            rs.close();
            stmt.close();
            return events;
        } catch (SQLException e) {
            throw new DAOException("Error in selectEventsByCity: " + e.getMessage(), e, GENERIC);
        }
    }

    public Event selectEvent(Integer idEvent) throws DAOException{
        Statement stmt;
        Event event = null;
        try {
            stmt = SingletonConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = EventQueries.selectEvent(stmt, idEvent);
            if (rs.next()) {
                event = fromResultSet(rs);
            }
            rs.close();
            stmt.close();
            return event;
        } catch (SQLException e) {
            throw new DAOException("Error in selectEvent: " + e.getMessage(), e, GENERIC);
        }
    }

    @Override
    public List<Event> selectEventsByOrganizer(String idOrganizer) throws DAOException {

        Statement stmt;
        List<Event> events = new ArrayList<>();
        try {
            stmt = SingletonConnector.getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = EventQueries.selectEventsByOrganizer(stmt, idOrganizer);
            while (rs.next()) {
                Event event = fromResultSet(rs);
                events.add(event);
            }
            rs.close();
            stmt.close();
            return events;
        } catch (SQLException e) {
            throw new DAOException("Error in selectEventsByOrganizer: " + e.getMessage(), e, GENERIC);
        }
    }

    private Event fromResultSet(ResultSet rs) throws SQLException {
        return new Event(rs.getInt(COLUMN_IDEVENT), rs.getString(COLUMN_NAME), rs.getString(COLUMN_DESC), rs.getString(COLUMN_LOCATION),
                rs.getString(COLUMN_ADDRESS), rs.getString(COLUMN_CITY), rs.getDate(COLUMN_DATE).toLocalDate(),
                rs.getTime(COLUMN_TIME).toLocalTime(), rs.getBoolean(COLUMN_BOOKINGCLOSED), rs.getString(COLUMN_ORGANIZER));
    }
}
