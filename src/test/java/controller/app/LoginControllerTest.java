package controller.app;

import bean.OrganizerBean;
import bean.UserBean;
import exception.DuplicateEntryException;
import exception.IncorrectDataException;
import exception.NotFoundException;
import exception.OperationFailedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void login() throws IncorrectDataException, OperationFailedException, NotFoundException {
        LoginController loginController = new LoginController();
        UserBean userBean = new UserBean();
        userBean.setUsername("pippo");
        userBean.setPassword("p.pippo");
        UserBean user = loginController.login(userBean);
        assertEquals("pippo", user.getUsername());
    }

    @Test
    void register() throws IncorrectDataException, OperationFailedException, DuplicateEntryException{
        OrganizerBean organizerBean = new OrganizerBean();
        organizerBean.setUsername("pluto");
        organizerBean.setPassword("p.pluto");
        organizerBean.setEmail("pluto@hotmail.com");
        organizerBean.setFirstName("Pluto");
        organizerBean.setLastName("Minati");
        organizerBean.setInfoPayPal("pluto@hotmail.com");
        LoginController loginController = new LoginController();
        UserBean user = loginController.register(organizerBean);
        assertEquals("pluto", user.getUsername());
        assertNotEquals("p.pluto", user.getPassword());
    }
}