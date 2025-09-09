package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.ProduitLudoDTO;
import com.example.heritage.ludo.model.ProduitLudo;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.ProduitLudoService;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/produit")
public class ProduitLudoController {

    @Autowired
    private ProduitLudoService produitLudoService;

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<ProduitLudo>> getAllProduitsLudo() {
        List<ProduitLudo> produits = produitLudoService.findAll();
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitLudo> getProduitsLudoById(@PathVariable Long id) {
        Optional<ProduitLudo> produits = produitLudoService.findById(id);
        return produits.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/categorie/{categorie}")
    public ResponseEntity<List<ProduitLudo>> getProduitsLudoByCategorie(@PathVariable String categorie) {
        List<ProduitLudo> produits = produitLudoService.findByCategorie(categorie);
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/search/{nom}")
    public ResponseEntity<List<ProduitLudo>> getProduitsLudoByNom(@PathVariable String nom) {
        List<ProduitLudo> produits = produitLudoService.findByNomContaining(nom);
        return ResponseEntity.ok(produits);
    }

    @GetMapping("/stock/{stock}")
    public ResponseEntity<List<ProduitLudo>> getProduitsLudoByStock(@PathVariable Integer stock) {
        List<ProduitLudo> produits = produitLudoService.findByStockGreaterThan(stock);
        return ResponseEntity.ok(produits);
    }

    @PostMapping
    public ResponseEntity<ProduitLudo> createProduitsLudo(@RequestBody ProduitLudoDTO produitLudoDTO) {
        Optional<PersonneLudo> personneLudo = personneLudoService.findById(produitLudoDTO.getPersonneLudoId());
        
        if (personneLudo.isPresent()) {
            ProduitLudo produit = new ProduitLudo();
            produit.setNom(produitLudoDTO.getNom());
            produit.setDescription(produitLudoDTO.getDescription());
            produit.setPrix(produitLudoDTO.getPrix());
            produit.setStock(produitLudoDTO.getStock());
            produit.setCategorie(produitLudoDTO.getCategorie());
            
            ProduitLudo savedProduit = produitLudoService.save(produit);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduit);
        }
        
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProduitLudo> updateProduitsLudo(@PathVariable Long id, @RequestBody ProduitLudoDTO produitLudoDTO) {
        Optional<ProduitLudo> existingProduit = produitLudoService.findById(id);
        
        if (existingProduit.isPresent()) {
            Optional<PersonneLudo> personneLudo = personneLudoService.findById(produitLudoDTO.getPersonneLudoId());
            
            if (personneLudo.isPresent()) {
                ProduitLudo produit = existingProduit.get();
                produit.setNom(produitLudoDTO.getNom());
                produit.setDescription(produitLudoDTO.getDescription());
                produit.setPrix(produitLudoDTO.getPrix());
                produit.setStock(produitLudoDTO.getStock());
                produit.setCategorie(produitLudoDTO.getCategorie());

                ProduitLudo updatedProduit = produitLudoService.save(produit);
                return ResponseEntity.ok(updatedProduit);
            }
            
            return ResponseEntity.badRequest().build();
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduitsLudo(@PathVariable Long id) {
        if (produitLudoService.existsById(id)) {
            produitLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
