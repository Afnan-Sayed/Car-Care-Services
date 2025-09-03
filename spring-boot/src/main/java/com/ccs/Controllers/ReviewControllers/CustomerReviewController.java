package com.ccs.Controllers.ReviewControllers;

import com.ccs.Models.DTOs.ReviewCreateRequest;
import com.ccs.Models.Review;
import com.ccs.Services.ReviewService.CustomerReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers/{customerId}/orders/{orderId}/reviews")
@RequiredArgsConstructor
public class CustomerReviewController {

    private final CustomerReviewService customerReviewService;

    @PostMapping
    public Review addReview(@PathVariable Long customerId,
                            @PathVariable Long orderId,
                            @RequestBody ReviewCreateRequest request) {
        return customerReviewService.addReview(customerId, orderId, request);
    }

    @GetMapping
    public Review getReview(@PathVariable Long customerId,
                            @PathVariable Long orderId) {
        return customerReviewService.getReview(customerId, orderId);
    }
}
