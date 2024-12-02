package main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GuestTest {

    @Test
    public void testGuestConstructorAndGetters() {
        // Create a Guest object
        Guest guest = new Guest(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        // Verify constructor and getters
        assertEquals(1, guest.getGuestID(), "Guest ID should be 1");
        assertEquals("John Doe", guest.getName(), "Name should be 'John Doe'");
        assertEquals("john.doe@example.com", guest.getEmail(), "Email should be 'john.doe@example.com'");
        assertEquals("123-456-7890", guest.getPhone(), "Phone should be '123-456-7890'");
        assertEquals("123 Main St", guest.getAddress(), "Address should be '123 Main St'");
    }

    @Test
    public void testSetGuestID() {
        // Create a Guest object
        Guest guest = new Guest(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        // Set new guest ID
        guest.setGuestID(2);

        // Verify setter
        assertEquals(2, guest.getGuestID(), "Guest ID should be updated to 2");
    }

    @Test
    public void testSetName() {
        // Create a Guest object
        Guest guest = new Guest(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        // Set new name
        guest.setName("Jane Doe");

        // Verify setter
        assertEquals("Jane Doe", guest.getName(), "Name should be updated to 'Jane Doe'");
    }

    @Test
    public void testSetEmail() {
        // Create a Guest object
        Guest guest = new Guest(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        // Set new email
        guest.setEmail("jane.doe@example.com");

        // Verify setter
        assertEquals("jane.doe@example.com", guest.getEmail(), "Email should be updated to 'jane.doe@example.com'");
    }

    @Test
    public void testSetPhone() {
        // Create a Guest object
        Guest guest = new Guest(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        // Set new phone number
        guest.setPhone("987-654-3210");

        // Verify setter
        assertEquals("987-654-3210", guest.getPhone(), "Phone should be updated to '987-654-3210'");
    }

    @Test
    public void testSetAddress() {
        // Create a Guest object
        Guest guest = new Guest(1, "John Doe", "john.doe@example.com", "123-456-7890", "123 Main St");

        // Set new address
        guest.setAddr("456 Elm St");

        // Verify setter
        assertEquals("456 Elm St", guest.getAddress(), "Address should be updated to '456 Elm St'");
    }
}
