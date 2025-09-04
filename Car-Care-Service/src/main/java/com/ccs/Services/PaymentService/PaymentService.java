package com.ccs.Services.PaymentService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccs.Models.*;
import com.ccs.Repository.OrdersRepo;
import com.ccs.Repository.PaymentRepo;

/*
Rojeh
Epic 8: Payment APIs
•	POST /payments
•	GET /payments/{id}
•	GET /payments/customers/{id}

 */

@Service
public class PaymentService {
    @Autowired
    private  PaymentRepo paymentRepo;

    @Autowired
    private OrdersRepo ordersRepo;

    public Payment pay(Payment payment){
    // Fetch full order from DB
    Order order = ordersRepo.findById(Long.valueOf(payment.getOrder().getId()))
            .orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));

    // Validate order status and amount
    if(order.getStatus() == Order.Status.STATUS_COMPLETED &&
       payment.getAmount().equals(order.getServiceDetail().getService().getPrice())) {

        payment.setOrder(order); // attach managed order
        payment.setCustomer(order.getCustomer()); // set customer from order

        // Simulate payment method
        if ("Cash".equalsIgnoreCase(payment.getMethod())) {
            System.out.println("Simulating cash payment: Customer paid $" + payment.getAmount() + " in cash.");
        } else if ("Credit".equalsIgnoreCase(payment.getMethod())) {
            try {
                Thread.sleep(1000); // mimic processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Simulating credit card payment: Charged $" + payment.getAmount()+ " to customer's card.");
        } else {
            throw new IllegalArgumentException("Invalid payment method: Must be 'Cash' or 'Credit'");
        }

        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("SUCCESS");
        return paymentRepo.save(payment);
    }

    throw new IllegalArgumentException("Service must be completed and amount must match service price");
}

    public Payment getPayment(Long id){
        if(paymentRepo.findById(id).isPresent()){
            return paymentRepo.findById(id).get();
        }
        throw new IllegalArgumentException("Invalid payment ID");
    }

    public List<Payment> getPaymentByCustomer(Long customerID){
        return paymentRepo.findByCustomerId(customerID);
    }

}



