package com.ccs.Controllers.OrdersControllers;

import com.ccs.Models.DTOs.OrderCreateRequest;
import com.ccs.Models.Order;
import com.ccs.Services.OrdersService.CustomerOrdersService;
import com.ccs.Services.OrdersService.OrdersManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
Nour
Epic 7: Orders APIs
•	GET /customers/{id}/orders
•	POST /customers/{id}/orders/{id}/reviews
 */

@RestController
@RequestMapping("/customers/{customerId}/orders")
@RequiredArgsConstructor
public class CustomerOrdersController {
    private final OrdersManagementService ordersManagementService;
    private final CustomerOrdersService customerOrdersService;

    @PostMapping
    public Order createOrder(@PathVariable Long customerId,
                             @RequestBody OrderCreateRequest request) {
        return ordersManagementService.createOrder(customerId, request);
    }

    @GetMapping
    public List<Order> getCustomerOrders(@PathVariable Long customerId) {
        return customerOrdersService.getOrdersByCustomer(customerId);
    }

    @PutMapping("/{orderId}/cancel")
    public Order cancelOrder(@PathVariable Long customerId,
                             @PathVariable Long orderId) {
        return customerOrdersService.cancelOrder(customerId, orderId);
    }

    @DeleteMapping("/{orderId}/delete")
    public void deleteOrder(@PathVariable Long customerId,
                             @PathVariable Long orderId) {
        ordersManagementService.deleteOrder(customerId, orderId);
    }

}
