package com.ccs.Models.DTOs;
import com.ccs.Models.Provider;


public class ProviderSignupRequestDTO {
    private Provider provider;
    private String verificationStatus;
    private Float locationLat;
    private Float locationLong;
    private String nationalIdImage;

    // Getters and setters
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
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