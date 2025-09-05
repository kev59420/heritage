package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.TechnicienDTO;
import com.example.heritage.kevin.model.Technicien;
import com.example.heritage.kevin.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    @GetMapping
    public ResponseEntity<List<Technicien>> getAllTechniciens() {
        List<Technicien> techniciens = technicienService.findAll();
        return ResponseEntity.ok(techniciens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Technicien> getTechnicienById(@PathVariable Long id) {
        Optional<Technicien> technicien = technicienService.findById(id);
        return technicien.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialite/{specialite}")
    public ResponseEntity<List<Technicien>> getTechniciensBySpecialite(@PathVariable String specialite) {
        List<Technicien> techniciens = technicienService.findBySpecialite(specialite);
        return ResponseEntity.ok(techniciens);
    }

    @GetMapping("/niveau/{niveau}")
    public ResponseEntity<List<Technicien>> getTechniciensByNiveau(@PathVariable Integer niveau) {
        List<Technicien> techniciens = technicienService.findByNiveau(niveau);
        return ResponseEntity.ok(techniciens);
    }

    @PostMapping
    public ResponseEntity<Technicien> createTechnicien(@RequestBody TechnicienDTO technicienDTO) {
        Technicien technicien = new Technicien();
        technicien.setNom(technicienDTO.getNom());
        technicien.setDateNaissance(technicienDTO.getDateNaissance());
        technicien.setNumeroEmploye(technicienDTO.getNumeroEmploye());
        technicien.setDateEmbauche(technicienDTO.getDateEmbauche());
        technicien.setSpecialite(technicienDTO.getSpecialite());
        technicien.setNiveau(technicienDTO.getNiveau());
        
        Technicien savedTechnicien = technicienService.save(technicien);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTechnicien);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technicien> updateTechnicien(@PathVariable Long id, @RequestBody TechnicienDTO technicienDTO) {
        Optional<Technicien> existingTechnicien = technicienService.findById(id);
        
        if (existingTechnicien.isPresent()) {
            Technicien technicien = existingTechnicien.get();
            technicien.setNom(technicienDTO.getNom());
            technicien.setDateNaissance(technicienDTO.getDateNaissance());
            technicien.setNumeroEmploye(technicienDTO.getNumeroEmploye());
            technicien.setDateEmbauche(technicienDTO.getDateEmbauche());
            technicien.setSpecialite(technicienDTO.getSpecialite());
            technicien.setNiveau(technicienDTO.getNiveau());
            
            Technicien updatedTechnicien = technicienService.save(technicien);
            return ResponseEntity.ok(updatedTechnicien);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicien(@PathVariable Long id) {
        if (technicienService.existsById(id)) {
            technicienService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
