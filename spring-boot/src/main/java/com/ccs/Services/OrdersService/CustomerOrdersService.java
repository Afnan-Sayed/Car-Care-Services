package com.ccs.Services.OrdersService;

import com.ccs.Models.Order;
import com.ccs.Repository.CustomerRepo;
import com.ccs.Repository.OrdersRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
Nour
Epic 7: Orders APIs
•	GET /customers/{id}/orders
•	POST /customers/{id}/orders/{id}/reviews
 */

@Service
@RequiredArgsConstructor
public class CustomerOrdersService {
    private final CustomerRepo customerRepo;
    private final OrdersRepo ordersRepo;

    public List<Order> getOrdersByCustomer(Long customerId) {
        if (!customerRepo.existsById(customerId)) {
            throw new EntityNotFoundException("Customer not found");
        }
        return ordersRepo.findByCustomerId(customerId);
    }

    public Order cancelOrder(Long customerId, Long orderId) {
        Order order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new IllegalStateException("Not your order");
        }

        order.setStatus(Order.Status.STATUS_CANCELED);
        order.setUpdatedAt(LocalDateTime.now());

        return ordersRepo.save(order);
    }
}
