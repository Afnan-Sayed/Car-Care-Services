package com.ccs.Repository;
import com.ccs.Models.ProviderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Kareem
findById, update, getServices, addService, updateService, deleteService
*/
public interface ProviderDetailsRepository extends JpaRepository<ProviderDetails, Integer> {
}