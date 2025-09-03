package com.ccs.Models;

/*
id, customer_car_id, provider_id (null before admin acceptance), service_id, location_lat,
location_long, distance, service_price, total_price,
status (initiated / accepted / in_progress / completed / canceled),
created_at, updated_at
*/

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    public enum Status {
        STATUS_INITIATED, STATUS_ACCEPTED, STATUS_IN_PROGRESS, STATUS_COMPLETED, STATUS_CANCELED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "service_detail_id", nullable = false)
    private ServiceDetails serviceDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "car", nullable = false)
    private Car car;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    @Column( name = "location" )
    private Location location;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}

