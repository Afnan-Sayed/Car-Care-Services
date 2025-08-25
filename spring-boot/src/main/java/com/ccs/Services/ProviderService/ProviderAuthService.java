package com.ccs.Services.ProviderService;

import com.ccs.Models.Provider;
import com.ccs.Models.User;
import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import com.ccs.Repositories.ProviderRepository;
import com.ccs.Repositories.UserRepository;
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
    private ProviderRepository providerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean signupProvider(ProviderSignupRequestDTO req) {
        if (req.getEmail() == null || userRepository.findByEmail(req.getEmail()).isPresent()) {
            System.out.println("Email is already taken");
            return false;
        }

        if (req.getUsername() == null || userRepository.existsByUsername(req.getUsername())) {
            System.out.println("Username is already taken");
            return false;
        }

        Provider provider = new Provider();
        provider.setUsername(req.getUsername());
        provider.setPassword(passwordEncoder.encode(req.getPassword()));
        provider.setEmail(req.getEmail());
        provider.setPhone(req.getPhone());
        provider.setRole(User.Role.ROLE_PROVIDER);

        // Set provider-specific fields
        provider.setVerificationStatus("PENDING");
        provider.setLocationLat(req.getLocationLat());
        provider.setLocationLong(req.getLocationLong());
        provider.setNationalIdImage(req.getNationalIdImage());

        // Save
        providerRepository.save(provider);
        return true;
    }
}

