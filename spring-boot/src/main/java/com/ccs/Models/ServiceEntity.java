package com.ccs.Models;
/*
id, name (Wash, Tow, Battery…), description, base_price,
pricing_type (fixed / per_km / per_car_type), created_at, updated_at
 */


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "services")
@Inheritance(strategy = InheritanceType.JOINED)
public class ServiceEntity {

    public enum PricingType {
        FIXED, PER_KM, PER_CAR_TYPE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Double basePrice;

    @Enumerated(EnumType.STRING)
    private PricingType pricingType;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
