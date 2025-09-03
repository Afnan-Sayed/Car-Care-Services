package com.ccs.Models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "order_id", nullable = false) // Changed to order_id for clarity
    private Order order; // Changed from Integer to Order entity

    private Double amount;

    private String method; 

    private String status; 

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime paymentDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
}