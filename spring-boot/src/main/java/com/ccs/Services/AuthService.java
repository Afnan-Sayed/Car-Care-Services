package com.ccs.Services;

import com.ccs.Models.Customer;
import com.ccs.Models.User;
import com.ccs.Repositories.CustomerRepository;
import com.ccs.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean signupCustomer(User request) {
        if (request.getEmail() == null || userRepository.findByEmail(request.getEmail()).isPresent()) {
            System.out.println("Email is already taken");
            return false;
        }

        if (request.getUsername() == null || userRepository.existsByUsername(request.getUsername())) {
            System.out.println("Username is already taken");
            return false;
        }

        Customer customer = new Customer();
        customer.setUsername(request.getUsername());
        customer.setPassword(passwordEncoder.encode(request.getPassword()));
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setRole(User.Role.ROLE_CUSTOMER);

        customerRepository.save(customer);
        return true;
    }
}