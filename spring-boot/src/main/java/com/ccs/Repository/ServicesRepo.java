package com.ccs.Repository;

import com.ccs.Models.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
/*
Maya
findAll, save, update, delete
*/
@Repository
public interface ServicesRepo extends JpaRepository<ServiceEntity, Long> {
        List<ServiceEntity> findAll();
        void deleteById(Long id);
        ServiceEntity findByName(String name);
        boolean existsByNameAndIdNot(String name, Long id);
}
