package com.example.heritage.kevin.repository;

import com.example.heritage.kevin.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    
    List<Produit> findByCategorie(String categorie);
    
    List<Produit> findByNomContainingIgnoreCase(String nom);
    
    List<Produit> findByStockGreaterThan(Integer stock);
}
