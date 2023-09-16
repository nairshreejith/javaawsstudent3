package assignment.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import assignment.entity.User;

public class UserTest {

    @Test
    public void testGettersAndSetters() {
        // Create a sample User object
        User user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setEmail("john@example.com");
        user.setStatus("Active");
        user.setPhonenumber("123-456-7890");
        user.setDob("1990-01-01");
        user.setAddress("123 Main St");

        // Test the getters
        assertEquals(1L, user.getId());
        assertEquals("John", user.getFirstname());
        assertEquals("Doe", user.getLastname());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("Active", user.getStatus());
        assertEquals("123-456-7890", user.getPhonenumber());
        assertEquals("1990-01-01", user.getDob());
        assertEquals("123 Main St", user.getAddress());

        // Test the setters
        user.setId(2L);
        user.setFirstname("Jane");
        user.setLastname("Smith");
        user.setEmail("jane@example.com");
        user.setStatus("Inactive");
        user.setPhonenumber("987-654-3210");
        user.setDob("1985-05-15");
        user.setAddress("456 Elm St");

        assertEquals(2L, user.getId());
        assertEquals("Jane", user.getFirstname());
        assertEquals("Smith", user.getLastname());
        assertEquals("jane@example.com", user.getEmail());
        assertEquals("Inactive", user.getStatus());
        assertEquals("987-654-3210", user.getPhonenumber());
        assertEquals("1985-05-15", user.getDob());
        assertEquals("456 Elm St", user.getAddress());
    }
}
