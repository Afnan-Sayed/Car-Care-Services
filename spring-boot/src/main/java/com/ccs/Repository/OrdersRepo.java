package com.ccs.Repository;
/*
Nour
save, findById, findByCustomer, findByProvider, update, delete, history
*/

import com.ccs.Models.Customer;
import com.ccs.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepo extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByProviderId(Long providerId);
    List<Order> findByStatus(Order.Status status);
    List<Order> findByCustomerAndStatusIn(Customer custom, List<Order.Status> statuses);
    List<Order> findByProviderIsNullAndStatus(Order.Status status);
}
