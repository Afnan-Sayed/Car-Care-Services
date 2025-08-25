package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/*
id, customer_id, car_type_id, license_plate, model_year, color, created_at, updated_at
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "car_type_id")
    private Long carTypeId;

    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "model_year")
    private Integer modelYear;

    private String color;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
