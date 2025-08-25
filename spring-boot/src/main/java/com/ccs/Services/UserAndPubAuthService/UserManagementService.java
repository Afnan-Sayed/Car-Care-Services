package com.ccs.Services.UserAndPubAuthService;

import com.ccs.Models.User;
import com.ccs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
Ahmed
Epic 2: User APIs (Admin manage user profiles)
•	GET /users/{id}
•	PUT /users/{id}
•	DELETE /users/{id}
 */
@Service
public class UserManagementService {

    @Autowired
    private UserRepo userRepository;

    // GET /users - Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // GET /users/{id}
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // PUT /users/{id}
    public User updateUser(Long id, User userRequest) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update user details
        if (userRequest.getUsername() != null) {
            existingUser.setUsername(userRequest.getUsername());
        }
        if (userRequest.getEmail() != null) {
            existingUser.setEmail(userRequest.getEmail());
        }
        if (userRequest.getPhone() != null) {
            existingUser.setPhone(userRequest.getPhone());
        }
        if (userRequest.getRole() != null) {
            existingUser.setRole(userRequest.getRole());
        }

        return userRepository.save(existingUser);
    }

    // DELETE /users/{id}
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        userRepository.delete(user);
    }
}
