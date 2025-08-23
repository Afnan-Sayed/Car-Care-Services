package com.ccs.Services;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ccs.Models.*;
import com.ccs.Repositories.*;
//signupCustomer(SignupCustomerRequest req)
//signupProvider(SignupProviderRequest req)
//signupAdmin(SignupAdminRequest req)
//login(LoginRequest req)
//logout(token)
//forgotPassword(ForgotPasswordRequest req)
//resetPassword(ResetPasswordRequest req, token)

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProviderDetailsRepository providerDetailsRepository;

    public boolean signupProvider(@RequestBody Provider request, String verificationStatus, Float locationLat, Float locationLong, String nationalIdImage) {
        // Validate email
        if (request.getEmail() == null || userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Email is already taken");
            return false;
        }

        // Create Provider entity
        Provider provider = new Provider();
        provider.setUsername(request.getUsername());
        provider.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        provider.setEmail(request.getEmail());
        provider.setPhone(request.getPhone());

        // Save Provider to users table
        User savedUser = userRepository.save(provider);

        // Create and save ProviderDetails to providers table
        ProviderDetails providerDetails = new ProviderDetails(savedUser, verificationStatus, locationLat, locationLong, nationalIdImage);
        providerDetailsRepository.save(providerDetails);

        return true;
    }

}
