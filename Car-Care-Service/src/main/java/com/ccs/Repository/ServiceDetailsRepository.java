package com.ccs.Repository;

import com.ccs.Models.ServiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails, Long> {
    List<ServiceDetails> findByProviderId(Long providerId);
}

