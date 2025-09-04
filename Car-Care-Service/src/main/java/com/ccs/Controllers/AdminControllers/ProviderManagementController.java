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
@RequestMapping("/admin/providers")
public class ProviderManagementController
{
    @Autowired
    private ProviderManagementService providerManagementService;

    @PostMapping("/{id}/enable")
    public ResponseEntity<String> enableProvider(@PathVariable Long id) {
        return providerManagementService.enableProvider(id);
    }

    @PostMapping("/{id}/disable")
    public ResponseEntity<String> disableProvider(@PathVariable Long id) {
        return providerManagementService.disableProvider(id);
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<String> approveProvider(@PathVariable Long id) {
        return providerManagementService.approveProvider(id);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<String> rejectProvider(@PathVariable Long id) {
        return providerManagementService.rejectProvider(id);
    }

    // GET /admin/providers/pending
    @GetMapping("/pending")
    public ResponseEntity<?> viewPendingProviders() {
        return providerManagementService.viewPendingProviders();
    }

    // PUT /admin/providers/{id}/verify
    @PutMapping("/{id}/verify")
    public ResponseEntity<?> verifyProvider(@PathVariable Long id) {
        return providerManagementService.verifyProvider(id);
    }
}