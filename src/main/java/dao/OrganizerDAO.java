package dao;

import exception.dao.DAOException;
import model.Organizer;

public interface OrganizerDAO {

    public Organizer selectOrganizer(String idOrganizer) throws DAOException;

    public Organizer selectOrganizer(String username, String password) throws DAOException;

    public void insertOrganizer(Organizer organizer) throws DAOException;

}
