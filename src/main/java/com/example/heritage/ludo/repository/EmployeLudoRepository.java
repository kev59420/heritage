package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.EmployeLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeLudoRepository extends JpaRepository<EmployeLudo, Long> {
    
    Optional<EmployeLudo> findByNumeroEmploye(String numeroEmploye);
}
