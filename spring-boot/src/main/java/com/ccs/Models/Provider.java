package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "providers")
public class Provider extends User {

    @Column(name = "verification_status")
    private String verificationStatus;

    @Column(name = "location_lat")
    private Float locationLat;

    @Column(name = "location_long")
    private Float locationLong;

    @Column(name = "national_id_image")
    private String nationalIdImage;

    public Provider(String username, String password, String email, String phone,
                    String verificationStatus, Float locationLat, Float locationLong, String nationalIdImage) {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_PROVIDER);

        this.verificationStatus = verificationStatus;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
        this.nationalIdImage = nationalIdImage;
    }
}
