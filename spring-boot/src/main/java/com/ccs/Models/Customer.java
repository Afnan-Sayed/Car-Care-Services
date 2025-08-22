package com.ccs.Models;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("customer") // Matches ENUM value in database
public class Customer extends User {
    public Customer() {
    }

    public Customer(String username, String password, String email, String phone) {
        super(username, password, email, phone);
    }
    
}