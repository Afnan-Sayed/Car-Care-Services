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
    private CustomerDetailsRepository customerDetailsRepository;

    public boolean signupCustomer(@RequestBody User request) {
        // Validate email
        if (request.getEmail() == null || userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Email is already taken");
            return false;
        }

        // Create Customer entity
        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        // Save Customer to users table
        User savedUser = userRepository.save(customer);

        // Create and save CustomerDetails to customers table
        CustomerDetails customerDetails = new CustomerDetails(savedUser);
        customerDetailsRepository.save(customerDetails);

        return true;
    }
}
