package com.ccs.Services.ReviewService;

import com.ccs.Models.Review;
import com.ccs.Repository.ReviewRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProviderReviewService {

    private final ReviewRepo reviewRepo;
    public List<Review> getProviderReviews(Long providerId) {
        List<Review> reviews = reviewRepo.findAllByProviderId(providerId);

        if (reviews.isEmpty()) {
            throw new EntityNotFoundException("No reviews found for this provider");
        }

        return reviews;
    }
}
