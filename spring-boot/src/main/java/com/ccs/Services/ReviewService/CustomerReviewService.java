package com.ccs.Services.ReviewService;

import com.ccs.Models.*;

import com.ccs.Models.DTOs.ReviewCreateRequest;
import com.ccs.Repository.OrdersRepo;
import com.ccs.Repository.ReviewRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerReviewService {

    private final ReviewRepo reviewRepo;
    private final OrdersRepo ordersRepo;

    public Review addReview(Long customerId, Long orderId, ReviewCreateRequest request) {
        Order order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new IllegalStateException("This order does not belong to the customer");
        }

        if (order.getStatus() != Order.Status.STATUS_COMPLETED) {
            throw new IllegalStateException("Only completed orders can be reviewed");
        }

        if (reviewRepo.findByOrderId(orderId).isPresent()) {
            throw new IllegalStateException("Order already reviewed");
        }

        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        review.setOrder(order);

        return reviewRepo.save(review);
    }

    public Review getReview(Long customerId, Long orderId) {
        Order order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new IllegalStateException("This order does not belong to the customer");
        }

        return reviewRepo.findByOrderId(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
    }
}

