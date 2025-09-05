package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.TechnicienLudoDTO;
import com.example.heritage.ludo.model.TechnicienLudo;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.TechnicienLudoService;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/techniciens")
public class TechnicienLudoController {

    @Autowired
    private TechnicienLudoService technicienLudoService;

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<TechnicienLudo>> getAllTechniciensLudo() {
        List<TechnicienLudo> techniciens = technicienLudoService.findAll();
        return ResponseEntity.ok(techniciens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnicienLudo> getTechnicienLudoById(@PathVariable Long id) {
        Optional<TechnicienLudo> technicien = technicienLudoService.findById(id);
        return technicien.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<List<TechnicienLudo>> getTechniciensLudoBySpecialite(@PathVariable String specialite) {
        List<TechnicienLudo> techniciens = technicienLudoService.findBySpecialite(specialite);
        return ResponseEntity.ok(techniciens);
    }

    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<TechnicienLudo>> getTechniciensLudoByNiveau(@PathVariable Integer niveau) {
        List<TechnicienLudo> techniciens = technicienLudoService.findByNiveau(niveau);
        return ResponseEntity.ok(techniciens);
    }

    @PostMapping
    public ResponseEntity<TechnicienLudo> createTechnicienLudo(@RequestBody TechnicienLudoDTO technicienLudoDTO) {
        Optional<PersonneLudo> personneLudo = personneLudoService.findById(technicienLudoDTO.getPersonneLudoId());
        
        if (personneLudo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        TechnicienLudo technicienLudo = new TechnicienLudo();
        technicienLudo.setNom(technicienLudoDTO.getNom());
        technicienLudo.setDateNaissance(technicienLudoDTO.getDateNaissance());
        technicienLudo.setNumeroEmploye(technicienLudoDTO.getNumeroEmploye());
        technicienLudo.setDateEmbauche(technicienLudoDTO.getDateEmbauche());
        technicienLudo.setSpecialite(technicienLudoDTO.getSpecialite());
        technicienLudo.setNiveau(technicienLudoDTO.getNiveau());
        technicienLudo.setPersonneLudo(personneLudo.get());
        
        TechnicienLudo savedTechnicienLudo = technicienLudoService.save(technicienLudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTechnicienLudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnicienLudo> updateTechnicienLudo(@PathVariable Long id, @RequestBody TechnicienLudoDTO technicienLudoDTO) {
        Optional<TechnicienLudo> existingTechnicienLudo = technicienLudoService.findById(id);
        
        if (existingTechnicienLudo.isPresent()) {
            Optional<PersonneLudo> personneLudo = personneLudoService.findById(technicienLudoDTO.getPersonneLudoId());
            
            if (personneLudo.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            TechnicienLudo technicienLudo = existingTechnicienLudo.get();
            technicienLudo.setNom(technicienLudoDTO.getNom());
            technicienLudo.setDateNaissance(technicienLudoDTO.getDateNaissance());
            technicienLudo.setNumeroEmploye(technicienLudoDTO.getNumeroEmploye());
            technicienLudo.setDateEmbauche(technicienLudoDTO.getDateEmbauche());
            technicienLudo.setSpecialite(technicienLudoDTO.getSpecialite());
            technicienLudo.setNiveau(technicienLudoDTO.getNiveau());
            technicienLudo.setPersonneLudo(personneLudo.get());
            
            TechnicienLudo updatedTechnicienLudo = technicienLudoService.save(technicienLudo);
            return ResponseEntity.ok(updatedTechnicienLudo);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicienLudo(@PathVariable Long id) {
        if (technicienLudoService.existsById(id)) {
            technicienLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
