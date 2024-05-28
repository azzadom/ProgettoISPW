package dao.fs;

import dao.OrganizerDAO;
import engineering.dao.ObjectSerializationHandler;
import exception.dao.DAOException;
import model.Organizer;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

import static exception.dao.TypeDAOException.*;

public class OrganizerFS implements OrganizerDAO {

    private final String FILEPATH = "resources/Files/Organizer.ser";

    @Override
    public Organizer selectOrganizer(String idOrganizer) throws DAOException {
        try {
            ObjectSerializationHandler<Organizer> handler = new ObjectSerializationHandler<>(FILEPATH);
            List<Organizer> orgs = handler.findObject(org -> org.getUsername().equals(idOrganizer));
            Organizer org =  orgs.getFirst();
            org.setTransientParams();
            return org;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }


    @Override
    public Organizer selectOrganizer(String username, String password) throws DAOException {
        try {
            ObjectSerializationHandler<Organizer> handler = new ObjectSerializationHandler<>(FILEPATH);
            List<Organizer> orgs = handler.findObject(org -> org.getUsername().equals(username) && org.getPassword().equals(password));
            Organizer org =  orgs.getFirst();
            org.setTransientParams();
            return org;
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    public void insertOrganizer(Organizer organizer) throws DAOException {
        try {
            ObjectSerializationHandler<Organizer> handler = new ObjectSerializationHandler<>(FILEPATH);
            if (!(handler.findObject(uniqueKey(organizer.getUsername())).isEmpty())) {
                throw new DAOException("Username already exists", DUPLICATE);
            } else if (!(handler.findObject(uniquePredicate(organizer.getFiscalCode(),
                    organizer.getEmail())).isEmpty())) {
                throw new DAOException("Organizer already exists", DUPLICATE);
            }
            handler.writeObjects(organizer);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in insertOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        }
    }

    private Predicate<Organizer> uniqueKey(String username){
        return org -> org.getUsername().equals(username);
    }

    private Predicate<Organizer> uniquePredicate(String CF, String email){
        return org -> org.getFiscalCode().equals(CF) && org.getEmail().equals(email);
    }

}
