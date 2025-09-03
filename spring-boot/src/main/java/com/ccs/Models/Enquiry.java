package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/*
id, customer_id (nullable), provider_id (nullable), content,
status (new / in_progress / resolved),
created_at
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enquiries")
public class Enquiry {

    public enum Status {
        NEW, IN_PROGRESS, RESOLVED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "provider_id")
    private Long providerId;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Enumerated(EnumType.STRING)
    private Status status = Status.NEW;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
