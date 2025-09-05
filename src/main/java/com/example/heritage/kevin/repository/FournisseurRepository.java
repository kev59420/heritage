package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    
    List<Fournisseur> findBySocieteContainingIgnoreCase(String societe);
}
