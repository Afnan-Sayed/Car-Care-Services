package com.ccs.Controllers.AuthControllers.GeneralUserAuth;

import com.ccs.Models.DTOs.ForgotPasswordRequest;
import com.ccs.Models.DTOs.ResetPasswordRequest;
import com.ccs.Models.DTOs.VerifyCodeRequest;
import com.ccs.Services.UserAndPubAuthService.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class PasswordResetController
{
    private final PasswordResetService passwordResetService;

    public PasswordResetController(PasswordResetService passwordResetService)
    {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request)
    {
        passwordResetService.forgotPassword(request.getEmail());
        return ResponseEntity.ok("Password reset email sent!");
    }

    @PostMapping("/verify-token")
    public ResponseEntity<String> verifyToken(@RequestBody VerifyCodeRequest request) {
        boolean valid = passwordResetService.verifyToken(request.getToken(), request.getEmail());
        return ResponseEntity.ok(valid ? "Token is valid" : "Invalid token");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Passwords do not match!");
        }
        passwordResetService.resetPassword(request.getEmail(), request.getToken(), request.getNewPassword());
        return ResponseEntity.ok("Password has been reset successfully!");
    }
}

