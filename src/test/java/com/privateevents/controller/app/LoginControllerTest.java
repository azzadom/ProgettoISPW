package com.privateevents.controller.app;

import com.privateevents.bean.OrganizerBean;
import com.privateevents.bean.UserBean;
import com.privateevents.exception.DuplicateEntryException;
import com.privateevents.exception.IncorrectDataException;
import com.privateevents.exception.NotFoundException;
import com.privateevents.exception.OperationFailedException;
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
        organizerBean.setUsername("luca");
        organizerBean.setPassword("l.luca");
        organizerBean.setEmail("luca@outlook.it");
        organizerBean.setFirstName("Luca");
        organizerBean.setLastName("Messieri");
        organizerBean.setInfoPayPal("luca@gmail.com");
        LoginController loginController = new LoginController();
        UserBean user = loginController.register(organizerBean);
        assertEquals("luca", user.getUsername());
        assertNotEquals("l.luca", user.getPassword());
    }
}