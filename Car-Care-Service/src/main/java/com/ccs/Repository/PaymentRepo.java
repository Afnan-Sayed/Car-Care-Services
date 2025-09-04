package com.ccs.Repository;

import com.ccs.Models.Payment;

import java.util.List;

/*
Rojeh
save, findById, update, findByCustomer
*/

import org.springframework.data.jpa.repository.JpaRepository;
public interface PaymentRepo extends JpaRepository<Payment, Long>{
    List<Payment> findByCustomerId(Long customerId);
}
