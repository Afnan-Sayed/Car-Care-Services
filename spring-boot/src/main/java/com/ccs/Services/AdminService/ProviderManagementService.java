package com.ccs.Services.AdminService;

import com.ccs.Models.Customer;
import com.ccs.Models.Provider;
import com.ccs.Models.User;
import com.ccs.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


@Service
@RequiredArgsConstructor
public class ProviderManagementService {

    @Autowired
    private final UserRepo userRepo;
//
//    public ResponseEntity<List<User>> getAllCustomers() {
//        List<User> customers = userRepo.findAllByRole(User.Role.ROLE_CUSTOMER);
//        return ResponseEntity.ok(customers);
//    }

    public ResponseEntity<String> enableProvider(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Provider)) {
            return ResponseEntity.badRequest().body("Provider with ID " + id + " not found or is not a provider");
        }
        else if (user instanceof Provider provider &&
                provider.getApprovalStatus() != Provider.ApprovalStatus.APPROVED)
        {
            return ResponseEntity.badRequest()
                    .body("sorry, but this provider has not been approved yet");
        }
        Provider provider = (Provider) user;
        provider .setEnableStatus(Provider.EnableStatus.ENABLED);
        userRepo.save(provider);
        return ResponseEntity.ok("Provider with ID " + id + " has been enabled");
    }

    public ResponseEntity<String> disableProvider(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Provider)) {
            return ResponseEntity.badRequest().body("Provider with ID " + id + " not found or is not a provider");
        }
        Provider provider = (Provider) user;
        provider.setEnableStatus(Provider.EnableStatus.DISABLED);
        userRepo.save(provider);
        return ResponseEntity.ok("Provider with ID " + id + " has been disabled");
    }


    //////////////////////////

    public ResponseEntity<String> approveProvider(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Provider)) {
            return ResponseEntity.badRequest().body("Provider with ID " + id + " not found or is not a provider");
        }
        Provider provider = (Provider) user;
        provider .setApprovalStatus(Provider.ApprovalStatus.APPROVED);
        provider .setEnableStatus(Provider.EnableStatus.ENABLED);
        userRepo.save(provider);
        return ResponseEntity.ok("Provider with ID " + id + " has been approved");
    }

    public ResponseEntity<String> rejectProvider(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Provider)) {
            return ResponseEntity.badRequest().body("Provider with ID " + id + " not found or is not a provider");
        }
        else if (user instanceof Provider provider &&
                provider.getApprovalStatus() == Provider.ApprovalStatus.APPROVED)
        {
            return ResponseEntity.badRequest()
                    .body("Provider with ID " + id + " is already approved, if you wanna disable him/her, try DISABLE method instead");
        }
        Provider provider = (Provider) user;
        provider.setApprovalStatus(Provider.ApprovalStatus.REJECTED);
        provider .setEnableStatus(Provider.EnableStatus.DISABLED);
        userRepo.save(provider);
        return ResponseEntity.ok("Provider with ID " + id + " has been rejected");
    }


    public ResponseEntity<List<Provider>> viewPendingProviders()
    {
        List<User> users = userRepo.findAllByRole(User.Role.ROLE_PROVIDER);
        List<Provider> pendingProviders = users.stream()
                .filter(u -> u instanceof Provider)
                .map(u -> (Provider) u)
                .filter(p -> p.getApprovalStatus() == Provider.ApprovalStatus.PENDING)
                .toList();

        return ResponseEntity.ok(pendingProviders);
    }

    // PUT /providers/{id}/verify
    public ResponseEntity<Provider> verifyProvider(Long id)
    {
        User user = userRepo.findById(id).orElse(null);
        if (user == null || !(user instanceof Provider)) {
            return ResponseEntity.badRequest().build();
        }
        Provider provider = (Provider) user;
        //
        return ResponseEntity.ok(provider);
    }
}