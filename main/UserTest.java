package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        User user = new User(1, "john_doe", "password123", "john.doe@example.com", "123-456-7890");

        assertEquals(1, user.getUserID(), "User ID should match.");
        assertEquals("john_doe", user.getUserName(), "User name should match.");
        assertEquals("password123", user.getUserPass(), "User password should match.");
        assertEquals("john.doe@example.com", user.getUserEmail(), "User email should match.");
        assertEquals("123-456-7890", user.getUserPhone(), "User phone should match.");
    }

    @Test
    public void testUserSetters() {
        User user = new User(1, "john_doe", "password123", "john.doe@example.com", "123-456-7890");

        user.setUserName("jane_doe");
        user.setUsePass("newpassword");
        user.setUseEmail("jane.doe@example.com");
        user.setUsePhone("987-654-3210");

        assertEquals("jane_doe", user.getUserName(), "User name should match after update.");
        assertEquals("newpassword", user.getUserPass(), "User password should match after update.");
        assertEquals("jane.doe@example.com", user.getUserEmail(), "User email should match after update.");
        assertEquals("987-654-3210", user.getUserPhone(), "User phone should match after update.");
    }
}
