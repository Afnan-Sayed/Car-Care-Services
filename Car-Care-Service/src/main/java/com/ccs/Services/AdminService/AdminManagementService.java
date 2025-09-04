package com.ccs.Services.AdminService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.ccs.Models.User;
import com.ccs.Repository.UserRepo;

import lombok.RequiredArgsConstructor;

/*
Maya
Epic 5:
•	GET /admins/{id}
•	PUT /admins/{id}
*/

@Service
@RequiredArgsConstructor

public class AdminManagementService {
    @Autowired
    private final UserRepo userRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<User> getAdminById(Long id) {
        return userRepo.findById(id).map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> updateAdmin(Long id, User admin) {
        if (userRepo.existsById(id)) {
            User existingAdmin = userRepo.findById(id).get();
            admin.setId(id);
            if(admin.getEmail() != null) existingAdmin.setEmail(admin.getEmail());
            if(admin.getPhone() != null) existingAdmin.setPhone(admin.getPhone());
            if(admin.getUsername() != null) existingAdmin.setUsername(admin.getUsername());
            if(admin.getPassword() != null) existingAdmin.setPassword(passwordEncoder.encode(admin.getPassword()));
            userRepo.save(existingAdmin);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body("Admin updated successfully");
        }
        else {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Validation invalid ID: " + id);
        }
    }
}
