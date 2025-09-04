package com.ccs.Controllers.ReviewControllers;

import com.ccs.Models.Review;
import com.ccs.Services.ReviewService.ProviderReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers/{providerId}/reviews")
@RequiredArgsConstructor
public class ProviderReviewController {
    private final ProviderReviewService providerReviewService;

    @GetMapping
    public List<Review> getProviderReviews(@PathVariable Long providerId) {
        return providerReviewService.getProviderReviews(providerId);
    }
}

