package com.ccs.Services.AdminService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ccs.Models.User;
import com.ccs.Repository.UserRepo;

import lombok.RequiredArgsConstructor;

/*
Maya
Epic 5:
•	GET /admin/customers
*/
/// /////////////////////////
///
/*
Afnan
Epic 5:
•	POST /admin/customers/{id}/enable
•	POST /admin/customers/{id}/disable
 */

@Service
@RequiredArgsConstructor
public class CustomerManagementService {

    @Autowired
    private final UserRepo userRepo;

    public ResponseEntity<List<User>> getAllCustomers() {
        List<User> customers = userRepo.findAllByRole(User.Role.ROLE_CUSTOMER);
        return ResponseEntity.ok(customers);
    }
}
