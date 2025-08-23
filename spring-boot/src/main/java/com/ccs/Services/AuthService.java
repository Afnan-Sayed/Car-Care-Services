package com.ccs.Services;

import com.ccs.Models.LoginRequest;
import com.ccs.Models.LoginResponse;
import com.ccs.Models.User;
import com.ccs.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate the user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            // If authentication is successful, get user details
            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Generate JWT token
            String token = jwtService.generateToken(user);

            // Return success response
            return new LoginResponse(
                    token,
                    user.getEmail(),
                    user.getName(),
                    user.getRole().name(),
                    user.getId());

        } catch (AuthenticationException e) {
            // Return error response for invalid credentials
            return new LoginResponse("Invalid email or password");
        } catch (Exception e) {
            return new LoginResponse("Login failed: " + e.getMessage());
        }
    }

    public String logout(String token) {
        return "Logout successful";
    }

}
