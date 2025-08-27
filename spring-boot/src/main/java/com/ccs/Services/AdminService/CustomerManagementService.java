package com.ccs.Services.AdminService;

import java.util.List;

import com.ccs.Models.Customer;
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



@Service
@RequiredArgsConstructor
public class CustomerManagementService {

    @Autowired
    private final UserRepo userRepo;

    public ResponseEntity<List<User>> getAllCustomers() {
        List<User> customers = userRepo.findAllByRole(User.Role.ROLE_CUSTOMER);
        return ResponseEntity.ok(customers);
    }


/*
Afnan
Epic 5:
•	POST /admin/customers/{id}/enable
•	POST /admin/customers/{id}/disable
*/

    public ResponseEntity<String> enableCustomer(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Customer)) {
            return ResponseEntity.badRequest().body("Customer with ID " + id + " not found or is not a customer");
        }
        Customer customer = (Customer) user;
        customer.setStatus(Customer.Status.ENABLED);
        userRepo.save(customer);
        return ResponseEntity.ok("Customer with ID " + id + " has been enabled");
    }

    public ResponseEntity<String> disableCustomer(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Customer)) {
            return ResponseEntity.badRequest().body("Customer with ID " + id + " not found or is not a customer");
        }
        Customer customer = (Customer) user;
        customer.setStatus(Customer.Status.DISABLED);
        userRepo.save(customer);
        return ResponseEntity.ok("Customer with ID " + id + " has been disabled");
    }

}
