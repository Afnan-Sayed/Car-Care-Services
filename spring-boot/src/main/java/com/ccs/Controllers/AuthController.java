package com.ccs.Controllers;

//POST /signup/customer → call authService.signupCustomer()
//POST /signup/provider → call authService.signupProvider()
//POST /signup/admin → call authService.signupAdmin()
//POST /login → return JWT
//POST /logout → invalidate token (or blacklist if needed)
//POST /forgot-password → send reset email/code
//POST /reset-password → reset password using token

import com.ccs.Models.Customer;
import com.ccs.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup/customer")
    public ResponseEntity<?> signupCustomer(@RequestBody Customer request) {
        boolean success = authService.signupCustomer(request);
        if (success) {
            return ResponseEntity.ok("Customer registered successfully");
        } else {
            return ResponseEntity.badRequest().body("Error registering customer");
        }
    }


}