package com.ccs.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ccs.Models.Customer;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
