package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    
    Optional<Employe> findByNumeroEmploye(String numeroEmploye);
}
