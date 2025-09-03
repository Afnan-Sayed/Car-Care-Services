package com.ccs.Controllers.ServicesControllers;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccs.Services.ServiceManagementService.ServicesManagementService;
import com.ccs.Models.ServiceEntity; 
import com.ccs.Repository.ServicesRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import javax.swing.text.html.parser.Entity;
/*
Maya
•  Epic 6: Service APIs
•	GET /services
•	POST /services
•	PUT /services/{id}
•	DELETE /services/{id}

 */
@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private ServicesManagementService servicesManagementService;

    @Autowired
    private ServicesRepo servicesRepo;

    @GetMapping
    public List<ServiceEntity> getAllServices() {
        return servicesManagementService.getAllServices();
    }

    @PostMapping
    public ResponseEntity<String> createService(@RequestBody ServiceEntity service) {
        if (servicesRepo.findByName(service.getName()) != null) {
            return ResponseEntity.badRequest().body("Service with name " + service.getName() + " already exists");
        }
        if(service.getCarType() == null ) {
            return ResponseEntity.badRequest().body("carType should be one of: PRIVATE_CAR, BUS, TRUCK, MOTORCYCLE");
        }
        if(service.getPrice() == null) {
            return ResponseEntity.badRequest().body("price must not be null");
        }
        servicesManagementService.createService(service);
        return ResponseEntity.ok("Service created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateService(@PathVariable Long id, @RequestBody ServiceEntity service) {
        if (servicesRepo.findById(id) == null) {
            return ResponseEntity.badRequest().body("Service with id " + id + " not found");
        }
        else if (servicesRepo.findByName(service.getName()) != null &&
                !servicesRepo.findByName(service.getName()).getId().equals(id)) {
            return ResponseEntity.badRequest().body("Service with name " + service.getName() + " already exists, Cannot update");
        }
        servicesManagementService.updateService(id, service);
        return ResponseEntity.ok("Service updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteService(@PathVariable Long id) {
        servicesManagementService.deleteService(id);
        return ResponseEntity.ok("Service deleted successfully");
    }
}
