package com.ccs.Controllers.AdminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.ccs.Services.AdminService.CustomerManagementService;

import java.util.List;
import com.ccs.Models.User;


/*
Maya
Epic 5:
•	GET /admin/customers
*/
/// /////////////////////////

@RestController
@RequestMapping("/admin")
public class CustomerManagementController {

    @Autowired
    private CustomerManagementService customerManagementService;

    @GetMapping("/customers")
    public ResponseEntity<List<User>> getAllCustomers() {
        return customerManagementService.getAllCustomers();
    }
    
/*
Afnan
Epic 5:
•	POST /admin/customers/{id}/enable
•	POST /admin/customers/{id}/disable
 */
    @PostMapping("/customers/{id}/enable")
    public ResponseEntity<String> enableCustomer(@PathVariable Long id) {
        return customerManagementService.enableCustomer(id);
    }

    @PostMapping("/customers/{id}/disable")
    public ResponseEntity<String> disableCustomer(@PathVariable Long id) {
        return customerManagementService.disableCustomer(id);
    }

}
