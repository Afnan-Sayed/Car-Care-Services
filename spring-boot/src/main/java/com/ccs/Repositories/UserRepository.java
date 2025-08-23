package com.ccs.Repositories;

import com.ccs.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// findByEmail(), existsByEmail()
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
}