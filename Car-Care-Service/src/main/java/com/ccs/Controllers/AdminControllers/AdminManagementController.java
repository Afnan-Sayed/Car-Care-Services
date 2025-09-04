package com.ccs.Controllers.AdminControllers;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.Models.User;
import com.ccs.Services.AdminService.AdminManagementService;

import jakarta.validation.Valid;
/*
Maya
Epic 5:
•	GET /admins/{id}
•	PUT /admins/{id} 
*/
@RestController
@RequestMapping("/admins")
public class AdminManagementController {

    @Autowired
    private AdminManagementService adminManagementService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getAdminById(@PathVariable Long id) {
        return adminManagementService.getAdminById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @Valid @RequestBody User admin) {
        return adminManagementService.updateAdmin(id, admin);
    }

}
