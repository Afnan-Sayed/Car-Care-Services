package com.ccs.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "providers")
public class ProviderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // extras
    private String verificationStatus;
    private Float locationLat;
    private Float locationLong;
    private String nationalIdImage;

    public ProviderDetails() {}

    public ProviderDetails(User user, String verificationStatus, Float locationLat, Float locationLong, String nationalIdImage) {
        this.user = user;
        this.verificationStatus = verificationStatus;
        this.locationLat = locationLat;
        this.locationLong = locationLong;
        this.nationalIdImage = nationalIdImage;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public Float getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Float locationLat) {
        this.locationLat = locationLat;
    }

    public Float getLocationLong() {
        return locationLong;
    }

    public void setLocationLong(Float locationLong) {
        this.locationLong = locationLong;
    }

    public String getNationalIdImage() {
        return nationalIdImage;
    }

    public void setNationalIdImage(String nationalIdImage) {
        this.nationalIdImage = nationalIdImage;
    }

}
