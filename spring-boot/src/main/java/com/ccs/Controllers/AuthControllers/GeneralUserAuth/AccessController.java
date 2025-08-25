package com.ccs.Controllers.AuthControllers.GeneralUserAuth;

import com.ccs.Models.User;
import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import com.ccs.Services.ProviderService.ProviderAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Ahmed
login, logout
*/

/*
Maya
Epic 1:
POST signup/customer
*/

/*
Kareem
•Epic 1:	signup provider
 */

@RestController
@RequestMapping("/auth")
public class AccessController {

    @Autowired
    private ProviderAuthService providerAuthService;

    // ✅ Provider Sign-Up
    @PostMapping("/signup/provider")
    public ResponseEntity<String> signupProvider(@RequestBody ProviderSignupRequestDTO request) {
        boolean success = providerAuthService.signupProvider(request);

        return success ? ResponseEntity.ok("Provider registered successfully")
                : ResponseEntity.badRequest().body("Signup failed: Email or username already exists");
    }
}
