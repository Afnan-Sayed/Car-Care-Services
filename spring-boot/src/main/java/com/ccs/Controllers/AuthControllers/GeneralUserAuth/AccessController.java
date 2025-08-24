package com.ccs.Controllers.AuthControllers.GeneralUserAuth;

import com.ccs.Models.ApiResponse;
import com.ccs.Models.Customer;
import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import com.ccs.Models.LoginRequest;
import com.ccs.Models.LoginResponse;
import com.ccs.Services.UserAndPubAuthService.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//POST /signup/customer → call authService.signupCustomer()
//POST /signup/provider → call authService.signupProvider()
//POST /signup/admin → call authService.signupAdmin()
//POST /login → return JWT
//POST /logout → invalidate token (or blacklist if needed)
//POST /forgot-password → send reset email/code
//POST /reset-password → reset password using token

@RestController
@RequestMapping("/auth")
public class AccessController {
    @Autowired
    private UserAuthService authService;

/*
Ahmed
login, logout
*/
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

/*
Maya
Epic 1:
POST signup/customer
*/
    @PostMapping("/signup/customer")
    public ResponseEntity<ApiResponse<String>> signupCustomer(@RequestBody Customer request) {
        boolean success = authService.signupCustomer(request);
        if (success) {
            return ResponseEntity.ok(ApiResponse.success("Customer registered successfully"));
        } else {
            return ResponseEntity.badRequest().body(ApiResponse.error("Error registering customer"));
        }
    }

/*
Kareem
•Epic 1:	signup provider
 */

    @PostMapping("/signup/provider")
    public ResponseEntity<?> signupProvider(@RequestBody ProviderSignupRequestDTO request) {
        boolean success = authService.signupProvider(
                request.getProvider(),
                request.getVerificationStatus(),
                request.getLocationLat(),
                request.getLocationLong(),
                request.getNationalIdImage()
        );
        if (success) {
            return ResponseEntity.ok("Provider registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Error registering provider");
        }
    }

}