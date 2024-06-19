package com.privateevents.dao;

import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Organizer;

public interface OrganizerDAO {

    public Organizer selectOrganizer(String idOrganizer) throws DAOException;

    public Organizer selectOrganizer(String username, String password) throws DAOException;

    public void insertOrganizer(Organizer organizer) throws DAOException;

}
