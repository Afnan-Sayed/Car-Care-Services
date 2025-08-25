package com.ccs.Services.UserAndPubAuthService;

import com.ccs.Models.*;
import com.ccs.Repository.CustomerRepo;
import com.ccs.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class UserAuthService
{
    private final UserRepo userRepository;
    private final CustomerRepo customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    /*
    Ahmed
    login and logout
    */
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

    /////////////////////////////////////////////////

    /*
Maya
signup customer
*/
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

/// //////////////////////////

}