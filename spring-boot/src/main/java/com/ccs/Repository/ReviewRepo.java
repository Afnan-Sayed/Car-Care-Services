package com.ccs.Repository;

import com.ccs.Models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Long> {
    Optional<Review> findByOrderId(Long orderId);

    @Query("SELECT r FROM Review r WHERE r.order.provider.id = :providerId")
    List<Review> findAllByProviderId(@Param("providerId") Long providerId);

}
