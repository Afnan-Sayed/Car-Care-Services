package com.ccs.Services.ProviderService;

import com.ccs.Models.Provider;
import com.ccs.Models.User;
import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import com.ccs.Repository.*;
import com.ccs.Repository.ProviderRepository;
import com.ccs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
Kareem
•Epic 1:	signup provider
Epic 4:
•	GET /providers/{id}
•	PUT /providers/{id}
 */


@Service
public class ProviderAuthService {

    @Autowired
    private ProviderRepository providerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signupProvider(ProviderSignupRequestDTO request) {
        if (request.getEmail() == null || userRepo.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Email is already taken");
            return false;
        }

        if (request.getUsername() == null || userRepo.existsByUsername(request.getUsername())) {
            System.out.println("Username is already taken");
            return false;
        }

        Provider provider = new Provider();
        provider.setUsername(request.getUsername());
        provider.setPassword(passwordEncoder.encode(request.getPassword()));
        provider.setEmail(request.getEmail());
        provider.setPhone(request.getPhone());
        provider.setRole(User.Role.ROLE_PROVIDER);

        // Set provider-specific fields
        provider.setVerificationStatus("PENDING");
        provider.setLocationLat(request.getLocationLat());
        provider.setLocationLong(request.getLocationLong());
        provider.setNationalIdImage(request.getNationalIdImage());

        // Save
        providerRepo.save(provider);
        return true;
    }
}