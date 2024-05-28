package controller.app;

import bean.OrganizerBean;
import bean.UserBean;
import exception.DuplicateEntryException;
import exception.IncorrectDataException;
import exception.OperationFailedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    @Test
    void login() throws IncorrectDataException, OperationFailedException {
        LoginController loginController = new LoginController();
        UserBean userBean = new UserBean();
        userBean.setUsername("luca");
        userBean.setPassword("l.luca");
        UserBean user = loginController.login(userBean);
        assertEquals("luca", user.getUsername());
    }

    @Test
    void register() throws IncorrectDataException, OperationFailedException, DuplicateEntryException{
        OrganizerBean organizerBean = new OrganizerBean();
        organizerBean.setUsername("pippo");
        organizerBean.setPassword("p.pippo");
        organizerBean.setEmail("pippo@gmail.com");
        organizerBean.setFirstName("Pippo");
        organizerBean.setLastName("Mascara");
        organizerBean.setFiscalCode("MSCPPP80A01C352J");
        organizerBean.setInfoPayPal("pippo_wallet@gmail.com");
        LoginController loginController = new LoginController();
        UserBean user = loginController.register(organizerBean);
        assertEquals("pippo", user.getUsername());
        assertNotEquals("p.pippo", user.getPassword());
    }
}