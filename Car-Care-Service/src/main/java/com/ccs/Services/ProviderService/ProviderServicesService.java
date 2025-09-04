package com.ccs.Services.ProviderService;

import com.ccs.Models.ServiceDetails;
import com.ccs.Models.Provider;
import com.ccs.Repository.ServiceDetailsRepository;
import com.ccs.Repository.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProviderServicesService {

    @Autowired
    private ServiceDetailsRepository serviceDetailsRepository;

    @Autowired
    private ProviderRepository providerRepository;

    public List<ServiceDetails> getProviderServices(Long providerId) {
        return serviceDetailsRepository.findByProviderId(providerId);
    }

    public ServiceDetails addProviderService(Long providerId, ServiceDetails serviceDetails) {
        Optional<Provider> providerOpt = providerRepository.findById(providerId);
        if (providerOpt.isEmpty()) return null;
        serviceDetails.setProvider(providerOpt.get());
        return serviceDetailsRepository.save(serviceDetails);
    }

    public ServiceDetails updateProviderService(Long id, ServiceDetails serviceDetails) {
        Optional<ServiceDetails> existingOpt = serviceDetailsRepository.findById(id);
        if (existingOpt.isEmpty()) return null;
        ServiceDetails existing = existingOpt.get();
        existing.setEstimatedTime(serviceDetails.getEstimatedTime());
        existing.setService(serviceDetails.getService());
        return serviceDetailsRepository.save(existing);
    }

    public void deleteProviderService(Long id) {
        serviceDetailsRepository.deleteById(id);
    }
}
