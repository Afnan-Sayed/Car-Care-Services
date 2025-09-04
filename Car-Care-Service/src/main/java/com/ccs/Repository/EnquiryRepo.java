package com.ccs.Repository;

import com.ccs.Models.Enquiry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnquiryRepo extends JpaRepository<Enquiry, Long> {
    List<Enquiry> findByCustomerId(Long customerId);

    List<Enquiry> findByProviderId(Long providerId);

    List<Enquiry> findByStatus(Enquiry.Status status);
}
