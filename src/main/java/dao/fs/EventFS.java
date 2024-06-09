package dao.fs;

import dao.EventDAO;
import engineering.dao.ObjectSerializationHandler;
import exception.dao.DAOException;
import model.Event;

import static exception.dao.TypeDAOException.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class EventFS implements EventDAO {
    private static final String FILE_PATH = "resources/Files/Event.ser";

    @Override
    public List<Event> selectEventsByCity(String city) throws DAOException {
        try {
            ObjectSerializationHandler<Event> hanlder = new ObjectSerializationHandler<>(FILE_PATH);
            List <Event> events = hanlder.findObject(event -> event.getCity().equalsIgnoreCase(city) &&
                    event.getDate().isAfter(LocalDate.now()));
            for (Event event : events) {
                event.setTransientParams();
            }
            return events;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in selectEventsByCity: " + e.getMessage(), e.getCause(), GENERIC);
        }

    }

    public Event selectEvent(Integer idEvent) throws DAOException{
        try {
            ObjectSerializationHandler<Event> handler = new ObjectSerializationHandler<>(FILE_PATH);
            List<Event> events = handler.findObject(event -> event.getIdEvent().equals(idEvent));
            if (events.isEmpty()) {
                return null;
            }
            Event event = events.getFirst();
            event.setTransientParams();
            return event;
        } catch (IOException | NoSuchElementException | ClassNotFoundException e) {
            throw new DAOException("Error in selectEvent: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    @Override
    public List<Event> selectEventsByOrganizer(String idOrganizer) throws DAOException {
        try {
            ObjectSerializationHandler<Event> hanlder = new ObjectSerializationHandler<>(FILE_PATH);
            List<Event> events = hanlder.findObject(event -> event.getOrgUsername().equals(idOrganizer));
            for (Event event : events) {
                event.setTransientParams();
            }
            return events;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in selectEventsByOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    public void insertEvent(Event event) throws DAOException {
        try {
            ObjectSerializationHandler<Event> handler = new ObjectSerializationHandler<>(FILE_PATH);
            handler.writeObjects(event);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in insertEvent: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    Predicate<Event> uniquePredicate(String city, String nameEvent){
        return event -> event.getCity().equals(city) && event.getName().equals(nameEvent);
    }

}
