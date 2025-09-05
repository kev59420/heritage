package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.ManagerLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerLudoRepository extends JpaRepository<ManagerLudo, Long> {
    
    List<ManagerLudo> findByDepartement(String departement);
}
