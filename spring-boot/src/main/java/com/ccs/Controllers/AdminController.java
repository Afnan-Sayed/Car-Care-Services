package com.ccs.Controllers;

import com.ccs.Models.Admin;
import com.ccs.Models.Customer;
import com.ccs.Models.Provider;
import com.ccs.Repositories.AdminRepository;
import com.ccs.Repositories.CustomerRepository;
import com.ccs.Repositories.ProviderRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminRepository adminRepo;
    private final ProviderRepository providerRepo;
    private final CustomerRepository customerRepo;

    public AdminController(AdminRepository adminRepo, ProviderRepository providerRepo, CustomerRepository customerRepo) {
        this.adminRepo = adminRepo;
        this.providerRepo = providerRepo;
        this.customerRepo = customerRepo;
    }

    
    @PostMapping("/signup")
    public Admin signup(@Valid @RequestBody Admin admin) {
        return adminRepo.save(admin);
    }

    
    @GetMapping("/{id}")
    public Admin getAdmin(@PathVariable Long id) {
        return adminRepo.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
    }

    
    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin updated) {
        Admin admin = adminRepo.findById(id).orElseThrow(() -> new RuntimeException("Admin not found"));
        admin.setName(updated.getName());
        admin.setPhone(updated.getPhone());
        admin.setEmail(updated.getEmail());
        return adminRepo.save(admin);
    }

    
    @GetMapping("/providers/pending")
    public List<Provider> getPendingProviders() {
        return providerRepo.findByVerifiedFalse();
    }

    
    @PutMapping("/providers/{id}/verify")
    public Provider verifyProvider(@PathVariable Long id) {
        Provider provider = providerRepo.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        provider.setVerified(true);
        return providerRepo.save(provider);
    }

    
    @PostMapping("/providers/{id}/approve")
    public Provider approveProvider(@PathVariable Long id) {
        Provider provider = providerRepo.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        provider.setEnabled(true);
        return providerRepo.save(provider);
    }

    
    @PostMapping("/providers/{id}/reject")
    public Provider rejectProvider(@PathVariable Long id) {
        Provider provider = providerRepo.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        provider.setEnabled(false);
        return providerRepo.save(provider);
    }

    
    @PostMapping("/providers/{id}/enable")
    public Provider enableProvider(@PathVariable Long id) {
        Provider provider = providerRepo.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        provider.setEnabled(true);
        return providerRepo.save(provider);
    }

    
    @PostMapping("/providers/{id}/disable")
    public Provider disableProvider(@PathVariable Long id) {
        Provider provider = providerRepo.findById(id).orElseThrow(() -> new RuntimeException("Provider not found"));
        provider.setEnabled(false);
        return providerRepo.save(provider);
    }

    
    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepo.findAll();
    }

    
    @PostMapping("/customers/{id}/enable")
    public Customer enableCustomer(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setEnabled(true);
        return customerRepo.save(customer);
    }

    
    @PostMapping("/customers/{id}/disable")
    public Customer disableCustomer(@PathVariable Long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setEnabled(false);
        return customerRepo.save(customer);
    }
}
