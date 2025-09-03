package com.ccs.Services.OrdersService;
/*
Nour
Epic 7: Orders APIs
•	GET /providers/{id}/orders
•	GET /providers/{id}/nearby
*/

import com.ccs.Models.Order;
import com.ccs.Models.Provider;
import com.ccs.Models.ServiceDetails;
import com.ccs.Repository.OrdersRepo;
import com.ccs.Repository.ProviderRepository;
import com.ccs.Repository.ServiceDetailsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProviderOrdersService {
    private final ProviderRepository providerRepository;
    private final ServiceDetailsRepository serviceDetailsRepository;
    private final OrdersRepo ordersRepo;

    public List<Order> getOrdersByProvider(Long providerId) {
        if (!providerRepository.existsById(providerId)) {
            throw new EntityNotFoundException("Provider not found");
        }
        return ordersRepo.findByProviderId(providerId);
    }

    public Order acceptOrder(Long providerId, Long orderId) {
        Order order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getProvider() != null || order.getStatus() != Order.Status.STATUS_INITIATED) {
            throw new IllegalStateException("Order Unavailable");
        }

        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

        order.setProvider(provider);
        order.setStatus(Order.Status.STATUS_ACCEPTED);
        order.setUpdatedAt(LocalDateTime.now());

        return ordersRepo.save(order);
    }

    public Order updateOrderStatus(Long providerId, Long orderId, Order.Status status) {
        Order order = ordersRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        if (order.getProvider() == null || !order.getProvider().getId().equals(providerId)) {
            throw new IllegalStateException("Provider not assigned to this order");
        }

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        return ordersRepo.save(order);
    }

    // Providers get all nearby unassigned orders
    public List<Order> getNearbyOrders(Long providerId) {
        Provider provider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

        List<ServiceDetails> providerServices = serviceDetailsRepository.findByProviderId(providerId);

        List<Order> unassignedOrders = ordersRepo
                .findByProviderIsNullAndStatus(Order.Status.STATUS_INITIATED);

        return unassignedOrders.stream()
                .filter(order -> providerServices.contains(order.getServiceDetail()))
                .filter(order -> {
                    double dist = haversine(provider.getLocation().getLatitude(),
                            provider.getLocation().getLongitude(),
                            order.getLocation().getLatitude(),
                            order.getLocation().getLongitude());
                    return dist <= 15000; // 15 km in meters
                })
                .collect(Collectors.toList());
    }

    // -------------------------------
    // Utility: Haversine formula
    // -------------------------------

    // (Using ArcCos) -> Distance = R ⋅ arccos(sin(latA)⋅sin(latB)+cos(latA)⋅cos(latB)⋅cos(longA−longB))

    // (Using ArcTan2) ->
    // a = sin^2((lat2 - lat1) / 2) + cos(lat1) * cos(lat2) * sin^2((lon2 - lon1) / 2)
    // c = 2 * atan2( sqrt(a), sqrt(1 - a) )
    // distance = R * c

    private double haversine(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Distance in KM
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
