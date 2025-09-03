package com.ccs.Controllers.PaymentControllers;
/*
Rojeh
Epic 8: Payment APIs
•	POST /payments
•	GET /payments/{id}
•	GET /customers/{id}/payments

 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ccs.Models.Payment;
import com.ccs.Repository.CustomerRepo;
import com.ccs.Repository.PaymentRepo;
import com.ccs.Services.PaymentService.PaymentService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private PaymentRepo paymentRepository;

    @PostMapping
    public Payment postPayment(@RequestBody @Valid Payment payment) {
        if(payment.getAmount() != null && payment.getOrder() != null && payment.getMethod() != null){
            return paymentService.pay(payment);
        }
        throw new IllegalArgumentException("Invalid payment data");
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Long id) {
        if(paymentRepository.findById(id).isPresent()){
            return paymentService.getPayment(id);
        }
        throw new IllegalArgumentException("Invalid payment ID");
    }

    @GetMapping("/customers/{customerId}")
    public List<Payment> getPaymentsByCustomer(@PathVariable Long customerId) {
        if(customerRepo.findById(customerId).isPresent()){
            return paymentService.getPaymentByCustomer(customerId);
        }
        throw new IllegalArgumentException("Invalid customer ID");
    }
    
    

}
