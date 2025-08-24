package com.ccs.Models;
// inherit from User (extra: verification_status, lat, long, national_id_image).

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("provider")  // Matches role
public class Provider extends User {
    public Provider() {
    }

    public Provider(String username, String password, String email, String phone) {
        super(username, password, email, phone);
    }
}
