package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.TechnicienLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicienLudoRepository extends JpaRepository<TechnicienLudo, Long> {
    
    List<TechnicienLudo> findBySpecialite(String specialite);
    
    List<TechnicienLudo> findByNiveau(Integer niveau);
}
