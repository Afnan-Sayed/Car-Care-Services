package com.ccs.Repository;

import com.ccs.Models.Settings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/*
Rojeh
for settings: find, update
 */
@Repository
public interface SettingsRepo extends JpaRepository<Settings, Long> {
    // Since there should be only one settings record, we can use this method
    Optional<Settings> findFirstByOrderById();
}
