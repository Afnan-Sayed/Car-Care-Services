package com.ccs.Controllers.ProviderControllers;

import com.ccs.Models.Provider;
import com.ccs.Models.DTOs.ProviderSignupRequestDTO;
import com.ccs.Services.ProviderService.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/{id}")
    public ResponseEntity<Provider> getProviderById(@PathVariable Long id) {
        Provider provider = providerService.getProviderById(id);
        if (provider == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(provider);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> updateProvider(@PathVariable Long id, @RequestBody ProviderSignupRequestDTO providerDetails) {
        Provider updatedProvider = providerService.updateProvider(id, providerDetails);
        if (updatedProvider == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedProvider);
    }
}
