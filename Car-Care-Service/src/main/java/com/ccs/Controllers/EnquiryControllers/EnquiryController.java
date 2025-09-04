package com.ccs.Controllers.EnquiryControllers;

import com.ccs.Models.ApiResponse;
import com.ccs.Models.Enquiry;
import com.ccs.Models.User;
import com.ccs.Models.DTOs.EnquiryRequest;
import com.ccs.Repository.UserRepo;
import com.ccs.Services.EnquiryService.EnquiryService;
import com.ccs.Services.UserAndPubAuthService.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Enquiry APIs
•	GET /enquiries (Admin gets all enquiries)
•	POST /enquiries (Customer/Provider creates enquiry)
 */
@RestController
@RequestMapping("/enquiries")
public class EnquiryController {

    @Autowired
    private EnquiryService enquiryService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepository;

    private User getUserFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // GET /enquiries - Admin gets all enquiries
    @GetMapping
    public ResponseEntity<ApiResponse<List<Enquiry>>> getAllEnquiries(
            @RequestHeader("Authorization") String authHeader) {
        try {
            User user = getUserFromToken(authHeader);

            // Check if user is admin
            if (user.getRole() != User.Role.ROLE_ADMIN) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Only admins can view all enquiries"));
            }

            List<Enquiry> enquiries = enquiryService.getAllEnquiries();
            return ResponseEntity.ok(ApiResponse.success("Enquiries retrieved successfully", enquiries));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // POST /enquiries - Customer/Provider creates enquiry
    @PostMapping
    public ResponseEntity<ApiResponse<Enquiry>> createEnquiry(@RequestHeader("Authorization") String authHeader,
            @RequestBody EnquiryRequest request) {
        try {
            User user = getUserFromToken(authHeader);

            // Only customers and providers can create enquiries
            if (user.getRole() == User.Role.ROLE_ADMIN) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Admins cannot create enquiries"));
            }

            Enquiry enquiry = enquiryService.createEnquiry(user.getId(), request.getContent());
            return ResponseEntity.ok(ApiResponse.success("Enquiry created successfully", enquiry));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // GET /enquiries/my - Get logged-in user's enquiries
    @GetMapping("/my")
    public ResponseEntity<ApiResponse<List<Enquiry>>> getMyEnquiries(
            @RequestHeader("Authorization") String authHeader) {
        try {
            User user = getUserFromToken(authHeader);

            List<Enquiry> enquiries = enquiryService.getEnquiriesByUser(user.getId());
            return ResponseEntity.ok(ApiResponse.success("Your enquiries retrieved successfully", enquiries));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }

    // PUT /enquiries/{id}/status - Admin updates enquiry status
    @PutMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Enquiry>> updateEnquiryStatus(@RequestHeader("Authorization") String authHeader,
            @PathVariable Long id, @RequestParam String status) {
        try {
            User user = getUserFromToken(authHeader);

            // Check if user is admin
            if (user.getRole() != User.Role.ROLE_ADMIN) {
                return ResponseEntity.badRequest().body(ApiResponse.error("Only admins can update enquiry status"));
            }

            Enquiry.Status enquiryStatus = Enquiry.Status.valueOf(status.toUpperCase());
            Enquiry updatedEnquiry = enquiryService.updateEnquiryStatus(id, enquiryStatus);
            return ResponseEntity.ok(ApiResponse.success("Enquiry status updated successfully", updatedEnquiry));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.error(e.getMessage()));
        }
    }
}
