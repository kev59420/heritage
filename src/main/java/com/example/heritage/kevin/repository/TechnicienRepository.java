package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    
    List<Technicien> findBySpecialite(String specialite);
    
    List<Technicien> findByNiveau(Integer niveau);
}
