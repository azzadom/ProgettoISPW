package com.privateevents.model;

import com.privateevents.exception.EncryptionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUsername() throws EncryptionException {
        User user = new User("user", "password");
        assertEquals("user", user.getUsername());
    }

    @Test
    void checkPassword() throws EncryptionException {
        User user = new User("user", "password");
        assertTrue(user.checkPassword("password"));
    }
}