package com.example.heritage.ludo.repository;

import com.example.heritage.ludo.model.ProduitLudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitLudoRepository extends JpaRepository<ProduitLudo, Long> {
    
    List<ProduitLudo> findByCategorie(String categorie);
    
    List<ProduitLudo> findByNomContainingIgnoreCase(String nom);
    
    List<ProduitLudo> findByStockGreaterThan(Integer stock);
}
