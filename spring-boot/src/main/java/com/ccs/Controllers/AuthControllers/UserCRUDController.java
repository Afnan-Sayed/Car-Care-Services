package com.ccs.Controllers.AuthControllers;

import com.ccs.Models.ApiResponse;
import com.ccs.Models.User;
import com.ccs.Services.UserAndPubAuthService.UserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/*
Ahmed
Epic 2: User APIs (Admin manage user profiles)
•	GET /users/{id}
•	PUT /users/{id}
•	DELETE /users/{id}
 */
@RestController
@RequestMapping("/users")
public class UserCRUDController {

    @Autowired
    private UserManagementService userManagementService;

    // get all users
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        try {
            List<User> users = userManagementService.getAllUsers();
            return ResponseEntity.ok(ApiResponse.success("Users retrieved successfully", users));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // get user by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        try {
            User user = userManagementService.getUserById(id);
            return ResponseEntity.ok(ApiResponse.success("User retrieved successfully", user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // put user
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User userRequest) {
        try {
            User updatedUser = userManagementService.updateUser(id, userRequest);
            return ResponseEntity.ok(ApiResponse.success("User updated successfully", updatedUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long id) {
        try {
            userManagementService.deleteUser(id);
            return ResponseEntity.ok(ApiResponse.success("User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
