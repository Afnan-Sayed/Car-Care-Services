package com.ccs.Services.ProviderService;

import com.ccs.Models.Location;
import com.ccs.Models.Provider;
import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import com.ccs.Repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    public Provider getProviderById(Long id) {
        Optional<Provider> provider = providerRepository.findById(id);
        return provider.orElse(null);
    }

    public Provider updateProvider(Long id, ProviderSignupRequestDTO providerDetails) {
        Optional<Provider> optionalProvider = providerRepository.findById(id);
        if (optionalProvider.isEmpty()) {
            return null;
        }
        Provider provider = optionalProvider.get();
        provider.setUsername(providerDetails.getUsername());
        provider.setPassword(providerDetails.getPassword());
        provider.setEmail(providerDetails.getEmail());
        provider.setPhone(providerDetails.getPhone());
        provider.setLocation(new Location(providerDetails.getLocationLat(),providerDetails.getLocationLong()));
        provider.setNationalIdImage(providerDetails.getNationalIdImage());
        return providerRepository.save(provider);
    }
}
