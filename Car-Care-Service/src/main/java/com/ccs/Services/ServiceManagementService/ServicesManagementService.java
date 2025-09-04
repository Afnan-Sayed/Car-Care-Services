package com.ccs.Services.ServiceManagementService;
/*
Maya
•  Epic 6: Service APIs
•	GET /services
•	POST /services
•	PUT /services/{id}
•	DELETE /services/{id}

 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ccs.Repository.ServicesRepo;
import com.ccs.Models.ServiceEntity;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicesManagementService {
    @Autowired
    private final ServicesRepo servicesRepo;

    public List<ServiceEntity> getAllServices() {
        return servicesRepo.findAll();
    }

    public ServiceEntity createService(ServiceEntity service) {
        if (servicesRepo.findByName(service.getName()) != null) {
            throw new RuntimeException("Service with the same name already exists");
        }
        return servicesRepo.save(service);
    }

    public ServiceEntity updateService(Long id, ServiceEntity service) {
        // find existing service match the id
         java.util.Optional<ServiceEntity> existingServiceOpt = servicesRepo.findById(id);
        try {
            if (!existingServiceOpt.isPresent()) {
                throw new RuntimeException("Service with this id not found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error checking for existing service: " + e.getMessage());
        }
        if (service.getName() != null && 
            !service.getName().equals(service.getName()) && 
            servicesRepo.existsByNameAndIdNot(service.getName(), id)) {
            throw new IllegalArgumentException("Service with name " + service.getName() + " already exists");
        }
        service.setId(id);
        if(service.getPrice() != null) service.setPrice(service.getPrice());
        else service.setPrice(existingServiceOpt.get().getPrice());
        if(service.getName() != null) service.setName(service.getName());
        else service.setName(existingServiceOpt.get().getName());
        if(service.getDescription() != null) service.setDescription(service.getDescription());
        else service.setDescription(existingServiceOpt.get().getDescription());
        if(service.getCarType() != null) service.setCarType(service.getCarType());
        else service.setCarType(existingServiceOpt.get().getCarType());
        return servicesRepo.save(service);
    }

    public void deleteService(Long id) {
        servicesRepo.deleteById(id);
    }
}
