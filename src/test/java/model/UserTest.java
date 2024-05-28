package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void getUsername() {
        User user = new User("user", "password");
        assertEquals("user", user.getUsername());
    }

    @Test
    void checkPassword() {
        User user = new User("user", "password");
        assertTrue(user.checkPassword("password"));
    }
}