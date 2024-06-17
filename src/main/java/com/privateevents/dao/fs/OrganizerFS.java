package com.privateevents.dao.fs;

import com.privateevents.dao.OrganizerDAO;
import com.privateevents.utils.dao.ObjectSerializationHandler;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Organizer;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class OrganizerFS implements OrganizerDAO {

    private static final String FILE_PATH = "src/main/resources/data/Organizer.ser";

    @Override
    public Organizer selectOrganizer(String idOrganizer) throws DAOException {
        try {
            ObjectSerializationHandler<Organizer> handler = new ObjectSerializationHandler<>(FILE_PATH);
            List<Organizer> orgs = handler.findObject(org -> org.getUsername().equals(idOrganizer));
            if (orgs.isEmpty()) {
                return null;
            }
            Organizer org =  orgs.getFirst();
            org.setTransientParams();
            return org;
        } catch (IOException | ClassNotFoundException | IndexOutOfBoundsException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e, GENERIC);
        }
    }


    @Override
    public Organizer selectOrganizer(String username, String password) throws DAOException {
        try {
            ObjectSerializationHandler<Organizer> handler = new ObjectSerializationHandler<>(FILE_PATH);
            List<Organizer> orgs = handler.findObject(org -> org.getUsername().equals(username) && org.getPassword().equals(password));
            if (orgs.isEmpty()) {
                return null;
            }
            Organizer org =  orgs.getFirst();
            org.setTransientParams();
            return org;
        } catch (IOException | ClassNotFoundException | IndexOutOfBoundsException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e, GENERIC);
        }
    }

    public void insertOrganizer(Organizer organizer) throws DAOException {
        try {
            ObjectSerializationHandler<Organizer> handler = new ObjectSerializationHandler<>(FILE_PATH);
            if (!(handler.findObject(uniqueKey(organizer.getUsername())).isEmpty())) {
                throw new DAOException("Username already exists", DUPLICATE);
            } else if (!(handler.findObject(uniquePredicate(organizer.getEmail())).isEmpty())) {
                throw new DAOException("Organizer already exists", DUPLICATE);
            }
            handler.writeObjects(organizer);
        } catch (IOException | ClassNotFoundException e) {
            throw new DAOException("Error in insertOrganizer: " + e.getMessage(), e, GENERIC);
        }
    }

    private Predicate<Organizer> uniqueKey(String username){
        return org -> org.getUsername().equals(username);
    }

    private Predicate<Organizer> uniquePredicate(String email){
        return org -> org.getEmail().equals(email);
    }

}
