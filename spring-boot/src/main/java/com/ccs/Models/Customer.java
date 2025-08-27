package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer extends User {
    public enum Status {
        ENABLED, DISABLED
    }

    public Status getStatus() {return status;}
    public void setStatus(Status status)
    {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.status = status;
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.ENABLED; //default


    public Customer(String username, String password, String email, String phone) {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_CUSTOMER);
        this.status = Status.ENABLED;
    }

    public Customer(String username, String password, String email, String phone, Status status) {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_CUSTOMER);
        this.status = status;
    }
}
