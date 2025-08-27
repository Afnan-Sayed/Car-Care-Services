package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "providers")
public class Provider extends User
{
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

    @Column(name = "location_lat")
    private Float locationLat;

    @Column(name = "location_long")
    private Float locationLong;

    @Column(name = "national_id_image")
    private String nationalIdImage;

    public Provider(String username, String password, String email, String phone
                    /*String verificationStatus*/, Float locationLat, Float locationLong, String nationalIdImage)
    {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_PROVIDER);

//        this.verificationStatus = verificationStatus;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
        this.nationalIdImage = nationalIdImage;
        this.enableStatus = EnableStatus.DISABLED;
        this.approvalStatus = ApprovalStatus.PENDING;
    }
}