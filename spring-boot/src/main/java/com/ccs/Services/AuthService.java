package com.ccs.Services;

import com.ccs.Models.Customer;
import com.ccs.Models.LoginRequest;
import com.ccs.Models.LoginResponse;
import com.ccs.Models.User;
import com.ccs.Repositories.CustomerRepository;
import com.ccs.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;


    public LoginResponse login(LoginRequest request) {
        try {
            // Load user details by email
            UserDetails userDetails = customUserDetailsService.loadUserByEmail(request.getEmail());

            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), request.getPassword())
            );
            
            User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            // Generate JWT token
            String token = jwtService.generateToken(userDetails);
            
            return new LoginResponse(
                token,
                "Login successful",
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
            );
            
        } catch (Exception e) {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public String logout(String token) {
        jwtService.blacklistToken(token);
        return "Logout successful";
    }

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