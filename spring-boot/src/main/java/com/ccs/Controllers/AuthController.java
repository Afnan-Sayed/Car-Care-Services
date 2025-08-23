package com.ccs.Controllers;

//POST /signup/customer → call authService.signupCustomer()
//POST /signup/provider → call authService.signupProvider()
//POST /signup/admin → call authService.signupAdmin()
//POST /login → return JWT
//POST /logout → invalidate token (or blacklist if needed)
//POST /forgot-password → send reset email/code
//POST /reset-password → reset password using token

import com.ccs.Models.ApiResponse;
import com.ccs.Models.Customer;
import com.ccs.Models.LoginRequest;
import com.ccs.Models.LoginResponse;
import com.ccs.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        try {
            LoginResponse response = authService.login(request);
            return ResponseEntity.ok(ApiResponse.success("Login successful", response));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String authHeader) {
        try {
            // Extract token from Authorization header (remove "Bearer " prefix)
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.badRequest().body("Invalid Authorization header");
            }

            String token = authHeader.substring(7); // Remove "Bearer " prefix
            String message = authService.logout(token);

            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Logout failed: " + e.getMessage());
        }
    }

    @PostMapping("/signup/customer")
    public ResponseEntity<ApiResponse<String>> signupCustomer(@RequestBody Customer request) {
        boolean success = authService.signupCustomer(request);
        if (success) {
            return ResponseEntity.ok(ApiResponse.success("Customer registered successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error registering customer"));
        }
    }


}