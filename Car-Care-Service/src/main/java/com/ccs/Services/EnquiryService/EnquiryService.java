package com.ccs.Services.EnquiryService;

import com.ccs.Models.Enquiry;
import com.ccs.Models.User;
import com.ccs.Repository.EnquiryRepo;
import com.ccs.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnquiryService {

    @Autowired
    private EnquiryRepo enquiryRepository;

    @Autowired
    private UserRepo userRepository;

    // GET /enquiries - Admin gets all enquiries
    public List<Enquiry> getAllEnquiries() {
        return enquiryRepository.findAll();
    }

    // POST /enquiries - Customer/Provider creates enquiry
    public Enquiry createEnquiry(Long userId, String content) {
        // Verify user exists
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Enquiry enquiry = new Enquiry();
        enquiry.setContent(content);

        // Set customer_id or provider_id based on user role
        if (user.getRole() == User.Role.ROLE_CUSTOMER) {
            enquiry.setCustomerId(userId);
        } else if (user.getRole() == User.Role.ROLE_PROVIDER) {
            enquiry.setProviderId(userId);
        } else {
            throw new RuntimeException("Only customers and providers can create enquiries");
        }

        return enquiryRepository.save(enquiry);
    }

    // Get enquiries by user (customer or provider)
    public List<Enquiry> getEnquiriesByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (user.getRole() == User.Role.ROLE_CUSTOMER) {
            return enquiryRepository.findByCustomerId(userId);
        } else if (user.getRole() == User.Role.ROLE_PROVIDER) {
            return enquiryRepository.findByProviderId(userId);
        } else {
            throw new RuntimeException("Only customers and providers can view their enquiries");
        }
    }

    // Admin can update enquiry status
    public Enquiry updateEnquiryStatus(Long enquiryId, Enquiry.Status status) {
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new RuntimeException("Enquiry not found with id: " + enquiryId));

        enquiry.setStatus(status);
        return enquiryRepository.save(enquiry);
    }
}
