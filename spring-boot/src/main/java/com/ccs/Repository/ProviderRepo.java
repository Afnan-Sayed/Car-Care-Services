package com.ccs.Repository;

import com.ccs.Models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
/*
Kareem
findById, update, getServices, addService, updateService, deleteService
*/


@Repository
public interface ProviderRepo extends JpaRepository<Provider, Long> {
    Optional<Provider> findByUsername(String username);
    Optional<Provider> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
