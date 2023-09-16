package assignment.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import assignment.controller.UserController;
import assignment.entity.User;
import assignment.repository.UserRepository;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "Doe", "john@example.com", "Active"));
        userList.add(new User(2L, "Jane", "Smith", "jane@example.com", "Inactive"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> response = userController.getAllUsers();

        assertEquals(2, response.size());
        assertEquals("John", response.get(0).getFirstname());
        assertEquals("Jane", response.get(1).getFirstname());
    }

    @Test
    public void testSearchUsersWithQuery() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "Doe", "john@example.com", "Active"));
        userList.add(new User(2L, "Jane", "Smith", "jane@example.com", "Inactive"));

        when(userRepository.searchUsers("John")).thenReturn(userList);

        List<User> response = userController.searchUsers("John");

        assertEquals(2, response.size());
        assertEquals("John", response.get(0).getFirstname());
        assertEquals("Jane", response.get(1).getFirstname());
    }

    @Test
    public void testSearchUsersWithoutQuery() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "John", "Doe", "john@example.com", "Active"));
        userList.add(new User(2L, "Jane", "Smith", "jane@example.com", "Inactive"));

        when(userRepository.findAll()).thenReturn(userList);

        List<User> response = userController.searchUsers(null);

        assertEquals(2, response.size());
        assertEquals("John", response.get(0).getFirstname());
        assertEquals("Jane", response.get(1).getFirstname());
    }

    @Test
    public void testCreateUser() {
        User newUser = new User(3L, "Alice", "Johnson", "alice@example.com", "Active");

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        User user = new User();
        user.setFirstname("Alice");
        user.setLastname("Johnson");
        user.setEmail("alice@example.com");
        user.setStatus("Active");

        User response = userController.createUser(user);

        assertEquals(newUser, response);
    }

    @Test
    public void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User(userId, "John", "Doe", "john@example.com", "Active");
        User modifiedUserData = new User(userId, "Updated John", "Doe", "john@example.com", "Inactive");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(modifiedUserData);

        User response = userController.updateUser(userId, modifiedUserData);

        assertEquals(modifiedUserData, response);
    }

    @Test
    public void testUpdateUserNotFound() {
        Long userId = 1L;
        User modifiedUserData = new User(userId, "Updated John", "Doe", "john@example.com", "Inactive");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        User response = userController.updateUser(userId, modifiedUserData);

        assertNull(response);
    }

}
