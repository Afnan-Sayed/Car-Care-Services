package com.ccs.Controllers.OrdersControllers;
/*
Nour
Epic 7: Orders APIs
•	GET /providers/{id}/orders
•	GET /providers/{id}/nearby
 */

import com.ccs.Models.Order;
import com.ccs.Services.OrdersService.OrdersManagementService;
import com.ccs.Services.OrdersService.ProviderOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers/{providerId}/orders")
@RequiredArgsConstructor
public class ProviderOrdersController {
    private final ProviderOrdersService providerOrdersService;
    private final OrdersManagementService ordersManagementService;

    // Get all orders of this provider
    @GetMapping
    public List<Order> getProviderOrders(@PathVariable Long providerId) {
        return providerOrdersService.getOrdersByProvider(providerId);
    }

    // Get nearby unassigned orders
    @GetMapping("/nearby")
    public List<Order> getNearbyOrders(@PathVariable Long providerId) {
        return providerOrdersService.getNearbyOrders(providerId);
    }

    // Accept an order
    @PutMapping("/{orderId}/accept")
    public Order acceptOrder(@PathVariable Long providerId,
                             @PathVariable Long orderId) {
        return providerOrdersService.acceptOrder(providerId, orderId);
    }

    // Update order status (in-progress, completed, canceled)
    @PutMapping("/{orderId}/status/{status}")
    public Order updateOrderStatus(@PathVariable Long providerId,
                                   @PathVariable Long orderId,
                                   @PathVariable Order.Status status) {
        return providerOrdersService.updateOrderStatus(providerId, orderId, status);
    }
}

