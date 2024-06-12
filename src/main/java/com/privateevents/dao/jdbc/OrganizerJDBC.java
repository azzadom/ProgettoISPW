package com.privateevents.dao.jdbc;

import com.privateevents.dao.jdbc.queries.OrganizerQueries;
import com.privateevents.dao.OrganizerDAO;
import com.privateevents.exception.EncryptionException;
import com.privateevents.exception.dao.DAOException;
import com.privateevents.model.Organizer;

import static com.privateevents.exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrganizerJDBC implements OrganizerDAO {

    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_FIRSTNAME = "FirstName";
    private static final String COLUMN_LASTNAME = "LastName";
    private static final String COLUMN_INFO_PAYPAL = "InfoPayPal";

    @Override
    public Organizer selectOrganizer(String idOrganizer) throws DAOException {

        Organizer org = null;

        try (Statement stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            ResultSet rs = OrganizerQueries.selectOrganizer(stmt, idOrganizer);
            if (rs.first()) {
                org = fromResultSet(rs);
            }
            rs.close();
            return org;
        } catch (SQLException | EncryptionException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public Organizer selectOrganizer(String username, String password) throws DAOException {
        Organizer org = null;
        try (Statement stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            ResultSet rs = OrganizerQueries.selectOrganizer(stmt, username, password);
            if (rs.first()) {
                org = fromResultSet(rs);
            }
            rs.close();
            return org;
        } catch (SQLException | EncryptionException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public void insertOrganizer(Organizer organizer) throws DAOException {
        try (Statement stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY)){
            OrganizerQueries.insertOrganizer(stmt, organizer.getUsername(), organizer.getPassword(),
                    organizer.getFirstName(), organizer.getLastName(),
                    organizer.getEmail(), organizer.getInfoPayPal());
        } catch (SQLException e) {
            if(e.getErrorCode() == 1061) {
                throw new DAOException("Username already exists", DUPLICATE);
            } else if(e.getErrorCode() == 1062) {
                throw new DAOException("Organizer already exists", DUPLICATE);
            }
            throw new DAOException("Error in insertOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    private Organizer fromResultSet(ResultSet rs) throws SQLException, EncryptionException {
        return new Organizer(rs.getString(COLUMN_USERNAME),rs.getString(COLUMN_PASSWORD),rs.getString(COLUMN_EMAIL), rs.getString(COLUMN_FIRSTNAME),
                rs.getString(COLUMN_LASTNAME), rs.getString(COLUMN_INFO_PAYPAL));
    }
}

