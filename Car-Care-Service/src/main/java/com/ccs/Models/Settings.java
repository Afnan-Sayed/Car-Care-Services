package com.ccs.Models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/*
id, platform_name, logo_url, about_image, about_description, terms_and_conditions,
facebook_url, whatsapp_number, phone_number, second_phone_number, price_per_km, created_at, updated_at
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "platform_name")
    private String platformName;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "about_image")
    private String aboutImage;

    @Column(name = "about_description", length = 2000)
    private String aboutDescription;

    @Column(name = "terms_and_conditions", length = 5000)
    private String termsAndConditions;

    @Column(name = "facebook_url")
    private String facebookUrl;

    @Column(name = "whatsapp_number")
    private String whatsappNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "second_phone_number")
    private String secondPhoneNumber;

    @Column(name = "price_per_km")
    private Float pricePerKm;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
