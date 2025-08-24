package com.ccs.Controllers;

import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.Services.AuthService;

//POST /signup/customer → call authService.signupCustomer()
//POST /signup/provider → call authService.signupProvider()
//POST /signup/admin → call authService.signupAdmin()
//POST /login → return JWT
//POST /logout → invalidate token (or blacklist if needed)
//POST /forgot-password → send reset email/code
//POST /reset-password → reset password using token

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

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
