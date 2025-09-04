package com.ccs.Services.SettingsService;

import com.ccs.Models.Settings;
import com.ccs.Repository.SettingsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
Rojeh
Epic 10: Settings & Enquiries
•	GET /settings
•	PUT /settings
 */
@Service
public class SettingsService {

    @Autowired
    private SettingsRepo settingsRepository;

    // GET /settings - Get current settings (public access)
    public Settings getSettings() {
        return settingsRepository.findFirstByOrderById()
                .orElse(createDefaultSettings());
    }

    // PUT /settings - Update settings (admin only)
    public Settings updateSettings(Settings settingsRequest) {
        Settings existingSettings = settingsRepository.findFirstByOrderById()
                .orElse(createDefaultSettings());

        // Update fields if provided
        if (settingsRequest.getPlatformName() != null) {
            existingSettings.setPlatformName(settingsRequest.getPlatformName());
        }
        if (settingsRequest.getLogoUrl() != null) {
            existingSettings.setLogoUrl(settingsRequest.getLogoUrl());
        }
        if (settingsRequest.getAboutImage() != null) {
            existingSettings.setAboutImage(settingsRequest.getAboutImage());
        }
        if (settingsRequest.getAboutDescription() != null) {
            existingSettings.setAboutDescription(settingsRequest.getAboutDescription());
        }
        if (settingsRequest.getTermsAndConditions() != null) {
            existingSettings.setTermsAndConditions(settingsRequest.getTermsAndConditions());
        }
        if (settingsRequest.getFacebookUrl() != null) {
            existingSettings.setFacebookUrl(settingsRequest.getFacebookUrl());
        }
        if (settingsRequest.getWhatsappNumber() != null) {
            existingSettings.setWhatsappNumber(settingsRequest.getWhatsappNumber());
        }
        if (settingsRequest.getPhoneNumber() != null) {
            existingSettings.setPhoneNumber(settingsRequest.getPhoneNumber());
        }
        if (settingsRequest.getSecondPhoneNumber() != null) {
            existingSettings.setSecondPhoneNumber(settingsRequest.getSecondPhoneNumber());
        }
        if (settingsRequest.getPricePerKm() != null) {
            existingSettings.setPricePerKm(settingsRequest.getPricePerKm());
        }

        return settingsRepository.save(existingSettings);
    }

    // Create default settings if none exist
    private Settings createDefaultSettings() {
        Settings defaultSettings = new Settings();
        defaultSettings.setPlatformName("Car Care Services");
        defaultSettings.setAboutDescription("Your trusted car care service provider");
        defaultSettings.setPhoneNumber("+1234567890");
        defaultSettings.setPricePerKm(2.5f);

        return settingsRepository.save(defaultSettings);
    }
}
