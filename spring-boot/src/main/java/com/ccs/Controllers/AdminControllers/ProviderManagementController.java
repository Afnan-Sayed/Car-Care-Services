package com.ccs.Controllers.AdminControllers;

import com.ccs.Models.User;
import com.ccs.Services.AdminService.CustomerManagementService;
import com.ccs.Services.AdminService.ProviderManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Afnan
Epic 5: Admin APIs
•	GET /providers/pending
•	PUT /providers/{id}/verify

•	POST /providers/{id}/approve
•	POST /providers/{id}/reject

•	POST /providers/{id}/enable
•	POST /providers/{id}/disable
 */
/// /////////////////////////

@RestController
@RequestMapping("/admin")
public class ProviderManagementController {

    @Autowired
    private ProviderManagementService providerManagementService;

    @PostMapping("/providers/{id}/enable")
    public ResponseEntity<String> enableCustomer(@PathVariable Long id) {
        return providerManagementService.enableProvider(id);
    }

    @PostMapping("/providers/{id}/disable")
    public ResponseEntity<String> disableCustomer(@PathVariable Long id) {
        return providerManagementService.disableProvider(id);
    }

    @PostMapping("/providers/{id}/approve")
    public ResponseEntity<String> approveCustomer(@PathVariable Long id) {
        return providerManagementService.approveProvider(id);
    }

    @PostMapping("/providers/{id}/reject")
    public ResponseEntity<String> rejectCustomer(@PathVariable Long id) {
        return providerManagementService.rejectProvider(id);
    }

}
