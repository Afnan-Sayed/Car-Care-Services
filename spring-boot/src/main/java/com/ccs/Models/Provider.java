package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "providers")
public class Provider extends User {
    public enum EnableStatus { ENABLED, DISABLED }
    public enum ApprovalStatus { PENDING, APPROVED, REJECTED }

    @Enumerated(EnumType.STRING)
    @Column(name = "enable_status")
    private EnableStatus enableStatus = EnableStatus.DISABLED; //default

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING; //default

//    @Column(name = "verification_status")
//    private String verificationStatus;

    @Embedded
    @Column( name = "location" )
    private Location location;

    @Column(name = "national_id_image")
    private String nationalIdImage;

    public Provider(String username, String password, String email, String phone,
                /*     String verificationStatus,*/ Double latitude, Double longitude, String nationalIdImage) {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_PROVIDER);

      //  this.verificationStatus = verificationStatus;
        this.location = new Location(latitude, longitude);
        this.nationalIdImage = nationalIdImage;
    }
}