package com.ccs.Controllers.ProviderControllers;

import com.ccs.Models.ServiceDetails;
import com.ccs.Services.ProviderService.ProviderServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/providers")
public class ProviderServicesController {

    @Autowired
    private ProviderServicesService providerServicesService;

    @GetMapping("/{id}/services")
    public ResponseEntity<List<ServiceDetails>> getProviderServices(@PathVariable Long id) {
        List<ServiceDetails> services = providerServicesService.getProviderServices(id);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/{id}/services")
    public ResponseEntity<ServiceDetails> addProviderService(@PathVariable Long id, @RequestBody ServiceDetails serviceDetails) {
        ServiceDetails created = providerServicesService.addProviderService(id, serviceDetails);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/services/{id}")
    public ResponseEntity<ServiceDetails> updateProviderService(@PathVariable Long id, @RequestBody ServiceDetails serviceDetails) {
        ServiceDetails updated = providerServicesService.updateProviderService(id, serviceDetails);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/services/{id}")
    public ResponseEntity<Void> deleteProviderService(@PathVariable Long id) {
        providerServicesService.deleteProviderService(id);
        return ResponseEntity.ok().build();
    }
}
