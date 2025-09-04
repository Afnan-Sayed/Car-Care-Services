package com.ccs.Controllers.SettingsControllers;

import com.ccs.Models.Settings;
import com.ccs.Services.UserAndPubAuthService.JwtService;
import com.ccs.Services.SettingsService.SettingsService;
import com.ccs.Models.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
Rojeh
Epic 10: Settings & Enquiries
•	GET /settings
•	PUT /settings
 */
@RestController
@RequestMapping("/settings")
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private JwtService jwtService;

    // GET /settings - Authenticated users only endpoint to get current settings
    @GetMapping
    public ResponseEntity<ApiResponse<Settings>> getSettings(
            @RequestHeader("Authorization") String authHeader) {
        try {
            // Extract token from Authorization header
            String token = authHeader.replace("Bearer ", "");

            // Validate token (any authenticated user can access)
            String username = jwtService.extractUsername(token);
            if (username == null || username.isEmpty()) {
                return ResponseEntity.status(401).body(
                        new ApiResponse<>(false, "Invalid or expired token.", null));
            }

            Settings settings = settingsService.getSettings();
            return ResponseEntity.ok(new ApiResponse<>(true, "Settings retrieved successfully", settings));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to retrieve settings: " + e.getMessage(), null));
        }
    }

    // PUT /settings - Admin only endpoint to update settings
    @PutMapping
    public ResponseEntity<ApiResponse<Settings>> updateSettings(
            @RequestHeader("Authorization") String authHeader,
            @RequestBody Settings settingsRequest) {
        try {
            // Extract token from Authorization header
            String token = authHeader.replace("Bearer ", "");

            // Extract username from token
            String username = jwtService.extractUsername(token);

            // Check if user is admin (assuming admin username is "admin")
            if (!"admin".equals(username)) {
                return ResponseEntity.status(403).body(
                        new ApiResponse<>(false, "Access denied. Only admin can update settings.", null));
            }

            Settings updatedSettings = settingsService.updateSettings(settingsRequest);
            return ResponseEntity.ok(new ApiResponse<>(true, "Settings updated successfully", updatedSettings));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to update settings: " + e.getMessage(), null));
        }
    }
}
