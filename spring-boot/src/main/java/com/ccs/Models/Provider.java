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

    @Embedded
    @Column( name = "location" )
    private Location location;

    @Column(name = "national_id_image")
    private String nationalIdImage;

    public Provider(String username, String password, String email, String phone,
                    String verificationStatus, Double latitude, Double longitude, String nationalIdImage) {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_PROVIDER);

        this.verificationStatus = verificationStatus;
        this.location = new Location(latitude, longitude);
        this.nationalIdImage = nationalIdImage;
    }
}