package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.FournisseurDTO;
import com.example.heritage.kevin.model.Fournisseur;
import com.example.heritage.kevin.service.FournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/fournisseurs")
public class FournisseurController {

    @Autowired
    private FournisseurService fournisseurService;

    @GetMapping
    public ResponseEntity<List<Fournisseur>> getAllFournisseurs() {
        List<Fournisseur> fournisseurs = fournisseurService.findAll();
        return ResponseEntity.ok(fournisseurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseurById(@PathVariable Long id) {
        Optional<Fournisseur> fournisseur = fournisseurService.findById(id);
        return fournisseur.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/societe/{societe}")
    public ResponseEntity<List<Fournisseur>> getFournisseursBySociete(@PathVariable String societe) {
        List<Fournisseur> fournisseurs = fournisseurService.findBySociete(societe);
        return ResponseEntity.ok(fournisseurs);
    }

    @PostMapping
    public ResponseEntity<Fournisseur> createFournisseur(@RequestBody FournisseurDTO fournisseurDTO) {
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setNom(fournisseurDTO.getNom());
        fournisseur.setDateNaissance(fournisseurDTO.getDateNaissance());
        fournisseur.setSociete(fournisseurDTO.getSociete());
        fournisseur.setContact(fournisseurDTO.getContact());
        
        Fournisseur savedFournisseur = fournisseurService.save(fournisseur);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFournisseur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable Long id, @RequestBody FournisseurDTO fournisseurDTO) {
        Optional<Fournisseur> existingFournisseur = fournisseurService.findById(id);
        
        if (existingFournisseur.isPresent()) {
            Fournisseur fournisseur = existingFournisseur.get();
            fournisseur.setNom(fournisseurDTO.getNom());
            fournisseur.setDateNaissance(fournisseurDTO.getDateNaissance());
            fournisseur.setSociete(fournisseurDTO.getSociete());
            fournisseur.setContact(fournisseurDTO.getContact());
            
            Fournisseur updatedFournisseur = fournisseurService.save(fournisseur);
            return ResponseEntity.ok(updatedFournisseur);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        if (fournisseurService.existsById(id)) {
            fournisseurService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
