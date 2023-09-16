package assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import assignment.entity.User;
import assignment.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Search users by name or email
    @GetMapping("/search/{query}")
    public List<User> searchUsers(@PathVariable String query) {
    	if (query != null && !query.isEmpty()) {
            return userRepository.searchUsers(query);
        } else {
            // If query is empty or null, return all users
            return userRepository.findAll();
        }
    }

    // Create a new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Update user by ID
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User modifiedUserData) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update user fields as needed
            user.setFirstname(modifiedUserData.getFirstname());
            // Update other fields similarly
            return userRepository.save(user);
        }
        return null; // User not found
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}