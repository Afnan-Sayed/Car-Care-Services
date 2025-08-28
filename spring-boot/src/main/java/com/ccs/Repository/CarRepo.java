package com.ccs.Repository;

import com.ccs.Models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {
    List<Car> findByCustomerId(Long customerId);
}