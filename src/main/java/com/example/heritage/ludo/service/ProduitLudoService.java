package com.example.heritage.ludo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heritage.ludo.model.ProduitLudo;
import com.example.heritage.ludo.repository.ProduitLudoRepository;

@Service
public class ProduitLudoService {

    @Autowired
    private ProduitLudoRepository produitLudoRepository;

    public List<ProduitLudo> findAll() {
        return produitLudoRepository.findAll();
    }

    public Optional<ProduitLudo> findById(Long id) {
        return produitLudoRepository.findById(id);
    }

    public List<ProduitLudo> findByCategorie(String categorie) {
        return produitLudoRepository.findByCategorie(categorie);
    }

    public List<ProduitLudo> findByNomContaining(String nom) {
        return produitLudoRepository.findByNomContainingIgnoreCase(nom);
    }

    public List<ProduitLudo> findByStockGreaterThan(Integer stock) {
        return produitLudoRepository.findByStockGreaterThan(stock);
    }

    public ProduitLudo save(ProduitLudo produitsLudo) {
        return produitLudoRepository.save(produitsLudo);
    }

    public void deleteById(Long id) {
        produitLudoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return produitLudoRepository.existsById(id);
    }
}
