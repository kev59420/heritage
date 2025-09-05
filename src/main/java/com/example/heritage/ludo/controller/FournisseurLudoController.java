package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.FournisseurLudoDTO;
import com.example.heritage.ludo.model.FournisseurLudo;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.FournisseurLudoService;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/fournisseurs")
public class FournisseurLudoController {

    @Autowired
    private FournisseurLudoService fournisseurLudoService;

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<FournisseurLudo>> getAllFournisseursLudo() {
        List<FournisseurLudo> fournisseurs = fournisseurLudoService.findAll();
        return ResponseEntity.ok(fournisseurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FournisseurLudo> getFournisseurLudoById(@PathVariable Long id) {
        Optional<FournisseurLudo> fournisseur = fournisseurLudoService.findById(id);
        return fournisseur.map(ResponseEntity::ok)
                          .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/societe/{societe}")
    public ResponseEntity<List<FournisseurLudo>> getFournisseursLudoBySociete(@PathVariable String societe) {
        List<FournisseurLudo> fournisseurs = fournisseurLudoService.findBySociete(societe);
        return ResponseEntity.ok(fournisseurs);
    }

    @PostMapping
    public ResponseEntity<FournisseurLudo> createFournisseurLudo(@RequestBody FournisseurLudoDTO fournisseurLudoDTO) {
        Optional<PersonneLudo> personneLudo = personneLudoService.findById(fournisseurLudoDTO.getPersonneLudoId());
        
        if (personneLudo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        FournisseurLudo fournisseurLudo = new FournisseurLudo();
        fournisseurLudo.setNom(fournisseurLudoDTO.getNom());
        fournisseurLudo.setDateNaissance(fournisseurLudoDTO.getDateNaissance());
        fournisseurLudo.setSociete(fournisseurLudoDTO.getSociete());
        fournisseurLudo.setContact(fournisseurLudoDTO.getContact());
        fournisseurLudo.setPersonneLudo(personneLudo.get());
        
        FournisseurLudo savedFournisseurLudo = fournisseurLudoService.save(fournisseurLudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFournisseurLudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FournisseurLudo> updateFournisseurLudo(@PathVariable Long id, @RequestBody FournisseurLudoDTO fournisseurLudoDTO) {
        Optional<FournisseurLudo> existingFournisseurLudo = fournisseurLudoService.findById(id);
        
        if (existingFournisseurLudo.isPresent()) {
            Optional<PersonneLudo> personneLudo = personneLudoService.findById(fournisseurLudoDTO.getPersonneLudoId());
            
            if (personneLudo.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            FournisseurLudo fournisseurLudo = existingFournisseurLudo.get();
            fournisseurLudo.setNom(fournisseurLudoDTO.getNom());
            fournisseurLudo.setDateNaissance(fournisseurLudoDTO.getDateNaissance());
            fournisseurLudo.setSociete(fournisseurLudoDTO.getSociete());
            fournisseurLudo.setContact(fournisseurLudoDTO.getContact());
            fournisseurLudo.setPersonneLudo(personneLudo.get());
            
            FournisseurLudo updatedFournisseurLudo = fournisseurLudoService.save(fournisseurLudo);
            return ResponseEntity.ok(updatedFournisseurLudo);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseurLudo(@PathVariable Long id) {
        if (fournisseurLudoService.existsById(id)) {
            fournisseurLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
