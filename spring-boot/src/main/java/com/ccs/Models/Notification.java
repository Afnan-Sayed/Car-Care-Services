package com.ccs.Models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = true)
    private Order order;

    private String message;

    public enum Type {
        TYPE_NEW_ORDER, TYPE_ORDER_COMPLETED, TYPE_PAYMENT_RECEIVED
    }

    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Role {
        ROLE_ADMIN, ROLE_PROVIDER, ROLE_CUSTOMER
    }

    @Enumerated(EnumType.STRING)
    private Role targetRole;

    private boolean isRead;

    private LocalDateTime createdAt;
}
