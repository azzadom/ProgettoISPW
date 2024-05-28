package dao;

import exception.dao.DAOException;
import model.Event;

import java.util.List;

public interface EventDAO {

    public List<Event> selectEventsByCity(String city) throws DAOException;

    public List<Event> selectEventsByOrganizer(String idOrganizer) throws DAOException;

    public Event selectEvent(Integer idEvent) throws DAOException;
}
