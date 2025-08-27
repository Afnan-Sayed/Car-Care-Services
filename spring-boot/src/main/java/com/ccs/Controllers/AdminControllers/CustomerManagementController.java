package com.ccs.Controllers.AdminControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import com.ccs.Services.AdminService.CustomerManagementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import com.ccs.Models.User;


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
@RestController
@RequestMapping("/admin")
public class CustomerManagementController {

    @Autowired
    private CustomerManagementService customerManagementService;

    @GetMapping("/customers")
    public ResponseEntity<List<User>> getAllCustomers() {
        return customerManagementService.getAllCustomers();
    }
    

}
