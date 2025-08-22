package com.ccs.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ccs.Models.Customer;
import com.ccs.Models.User;
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
