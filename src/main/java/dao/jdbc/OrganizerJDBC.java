package dao.jdbc;

import dao.OrganizerDAO;
import dao.jdbc.queries.OrganizerQueries;
import exception.dao.DAOException;
import model.Organizer;

import static exception.dao.TypeDAOException.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrganizerJDBC implements OrganizerDAO {


    @Override
    public Organizer selectOrganizer(String idOrganizer) throws DAOException {

        Statement stmt;
        Organizer org = null;

        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = OrganizerQueries.selectOrganizer(stmt, idOrganizer);
            if (rs.first()) {
                org = new Organizer(rs.getString("Username"),rs.getString("Password"),rs.getString("Email"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("FiscalCode"), rs.getString("InfoPayPal"));
            }
            rs.close();
            stmt.close();

            return org;
        } catch (SQLException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public Organizer selectOrganizer(String username, String password) throws DAOException {
        Statement stmt;
        Organizer org = null;

        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = OrganizerQueries.selectOrganizer(stmt, username, password);
            if (rs.first()) {
                org = new Organizer(rs.getString("Username"),rs.getString("Password"),rs.getString("Email"), rs.getString("FirstName"),
                        rs.getString("LastName"), rs.getString("FiscalCode"), rs.getString("InfoPayPal"));
            }
            rs.close();
            stmt.close();

            return org;
        } catch (SQLException e) {
            throw new DAOException("Error in selectOrganizer: " + e.getMessage(), e.getCause(), GENERIC);
        } finally {
            SingletonConnector.getConnector().endConnection();
        }
    }

    @Override
    public void insertOrganizer(Organizer organizer) throws DAOException {
        Statement stmt;
        try {
            stmt = SingletonConnector.getConnector().getConnection().createStatement();
            OrganizerQueries.insertOrganizer(stmt, organizer.getUsername(), organizer.getPassword(),
                    organizer.getFirstName(), organizer.getLastName(), organizer.getFiscalCode(),
                    organizer.getEmail(), organizer.getInfoPayPal());
            stmt.close();
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
}

