package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.ProduitDTO;
import com.example.heritage.kevin.model.Produit;
import com.example.heritage.kevin.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/produit")
public class ProduitController {

    @Autowired
    private ProduitService produitService;

    @GetMapping
    public ResponseEntity<List<Produit>> getAllProduits() {
        List<Produit> produits = produitService.findAll();
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitsById(@PathVariable Long id) {
        Optional<Produit> produits = produitService.findById(id);
        return produits.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<Produit>> getProduitsByCategorie(@PathVariable String categorie) {
        List<Produit> produits = produitService.findByCategorie(categorie);
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/search/{nom}")
    public ResponseEntity<List<Produit>> getProduitsByNom(@PathVariable String nom) {
        List<Produit> produits = produitService.findByNomContaining(nom);
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/stock/{stock}")
    public ResponseEntity<List<Produit>> getProduitsByStock(@PathVariable Integer stock) {
        List<Produit> produits = produitService.findByStockGreaterThan(stock);
        return ResponseEntity.ok(produits);
    }

    @PostMapping
    public ResponseEntity<Produit> createProduit(@RequestBody ProduitDTO produitDTO) {
        Produit produit = new Produit();
        produit.setNom(produitDTO.getNom());
        produit.setDescription(produitDTO.getDescription());
        produit.setPrix(produitDTO.getPrix());
        produit.setStock(produitDTO.getStock());
        produit.setCategorie(produitDTO.getCategorie());
        
        Produit savedProduit = produitService.save(produit);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody ProduitDTO produitDTO) {
        Optional<Produit> existingProduit = produitService.findById(id);

        if (existingProduit.isPresent()) {
            Produit produit = existingProduit.get();
            produit.setNom(produitDTO.getNom());
            produit.setDescription(produitDTO.getDescription());
            produit.setPrix(produitDTO.getPrix());
            produit.setStock(produitDTO.getStock());
            produit.setCategorie(produitDTO.getCategorie());

            Produit updatedProduit = produitService.save(produit);
            return ResponseEntity.ok(updatedProduit);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        if (produitService.existsById(id)) {
            produitService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
