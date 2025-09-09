package com.example.heritage.kevin.service;

import com.example.heritage.kevin.model.Produit;
import com.example.heritage.kevin.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {

    @Autowired
    private ProduitRepository produitsRepository;

    public List<Produit> findAll() {
        return produitsRepository.findAll();
    }

    public Optional<Produit> findById(Long id) {
        return produitsRepository.findById(id);
    }

    public List<Produit> findByCategorie(String categorie) {
        return produitsRepository.findByCategorie(categorie);
    }

    public List<Produit> findByNomContaining(String nom) {
        return produitsRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<Produit> findByStockGreaterThan(Integer stock) {
        return produitsRepository.findByStockGreaterThan(stock);
    }

    public Produit save(Produit produits) {
        return produitsRepository.save(produits);
    }

    public void deleteById(Long id) {
        produitsRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return produitsRepository.existsById(id);
    }
}
