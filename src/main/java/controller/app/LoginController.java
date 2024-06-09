package controller.app;

import bean.OrganizerBean;
import bean.UserBean;
import engineering.ToBeanConverter;
import engineering.dao.factory.FactorySingletonDAO;
import exception.*;
import exception.dao.DAOException;
import model.User;
import model.Organizer;

import java.util.logging.Level;
import java.util.logging.Logger;

import static exception.dao.TypeDAOException.DUPLICATE;

public class LoginController {

        public UserBean login(UserBean userBean) throws OperationFailedException, NotFoundException {
            try{
                User user = new User(userBean.getUsername(), userBean.getPassword());
                Organizer org = FactorySingletonDAO.getDefaultDAO().getOrganizerDAO().selectOrganizer(user.getUsername(), user.getPassword());
                if (org == null) {
                    throw new NotFoundException("User not found.");
                }
                return ToBeanConverter.fromOrganizerToOrganizerBean(org);
            } catch (DAOException e) {
                Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e.getCause());
                throw new OperationFailedException();
            } catch (IncorrectDataException | EncryptionException e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
                throw new OperationFailedException();
            }
        }

        public UserBean register(OrganizerBean orgBean) throws OperationFailedException, DuplicateEntryException {
            try{
                Organizer org = new Organizer(orgBean.getUsername(), orgBean.getPassword(), orgBean.getEmail(),
                        orgBean.getFirstName(), orgBean.getLastName(), orgBean.getInfoPayPal());
                FactorySingletonDAO.getDefaultDAO().getOrganizerDAO().insertOrganizer(org);
                return ToBeanConverter.fromOrganizerToOrganizerBean(org);
            } catch (DAOException e) {
                if (e.getTypeException().equals(DUPLICATE)) {
                    throw new DuplicateEntryException(e.getMessage());
                } else {
                    Logger.getAnonymousLogger().log(Level.WARNING, e.getMessage(), e.getCause());
                    throw new OperationFailedException();
                }
            } catch (IncorrectDataException | EncryptionException e) {
                Logger.getAnonymousLogger().log(Level.SEVERE, e.getMessage(), e.getCause());
                throw new OperationFailedException();
            }
        }
}
