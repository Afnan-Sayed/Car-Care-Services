package com.ccs.Services.OrdersService;
/*
Nour
Epic 7: Orders APIs
•	POST /orders
•	GET /orders/{id}
•	PUT /orders/{id}
•	DELETE /orders/{id}
•	GET /orders/history
 */

import com.ccs.Models.*;
import com.ccs.Models.DTOs.OrderCreateRequest;
import com.ccs.Repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersManagementService {
    private final OrdersRepo ordersRepo;
    private final CustomerRepo customerRepo;
    private final ServiceDetailsRepository serviceDetailsRepository;

    public Order createOrder(Long customerId, OrderCreateRequest request) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

//        Car car = Car.findById(request.getCarId())
//                .orElseThrow(() -> new EntityNotFoundException("Car not found"));

        ServiceDetails serviceDetail = serviceDetailsRepository.findById(request.getServiceDetailId())
                .orElseThrow(() -> new EntityNotFoundException("Service detail not found"));

        Order order = new Order();
        order.setCustomer(customer);
//        order.setCar(car);
        order.setServiceDetail(serviceDetail);
        order.setLocation(new Location(request.getLatitude(), request.getLongitude()));
        order.setStatus(Order.Status.STATUS_INITIATED);

        return ordersRepo.save(order);
    }

    public Order getOrderById(Long id) {
        return ordersRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

//    public Order updateOrder(Long id, Order updatedOrder) {
//        Order order = getOrderById(id);
//        order.setStatus(updatedOrder.getStatus());
//        order.setProvider(updatedOrder.getProvider());
//        order.setUpdatedAt(LocalDateTime.now());
//        return ordersRepo.save(order);
//    }

    public void deleteOrder(Long id, Long customerId) {
        Order order = ordersRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (!order.getCustomer().getId().equals(customerId)) {
            throw new IllegalStateException("Not your Order");
        }
        if (!order.getStatus().equals(Order.Status.STATUS_CANCELED) && !order.getStatus().equals(Order.Status.STATUS_INITIATED)) {
            throw new IllegalStateException("Can't delete order.");
        }
        ordersRepo.deleteById(id);
    }

    public List<Order> getOrderHistory(Long customerId) {
        Customer customer = customerRepo.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return ordersRepo.findByCustomerId(customerId).stream()
                .filter(o -> o.getStatus() == Order.Status.STATUS_COMPLETED ||
                        o.getStatus() == Order.Status.STATUS_CANCELED)
                .toList();
    }
}
