package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    
    List<Manager> findByDepartement(String departement);
}
