package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.PersonneDTO;
import com.example.heritage.kevin.model.Personne;
import com.example.heritage.kevin.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/personnes")
public class PersonneController {

    @Autowired
    private PersonneService personneService;

    @GetMapping
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        List<Personne> personnes = personneService.findAll();
        return ResponseEntity.ok(personnes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        Optional<Personne> personne = personneService.findById(id);
        return personne.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personne> createPersonne(@RequestBody PersonneDTO personneDTO) {
        Personne personne = new Personne();
        personne.setNom(personneDTO.getNom());
        personne.setDateNaissance(personneDTO.getDateNaissance());
        
        Personne savedPersonne = personneService.save(personne);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonne);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody PersonneDTO personneDTO) {
        Optional<Personne> existingPersonne = personneService.findById(id);
        
        if (existingPersonne.isPresent()) {
            Personne personne = existingPersonne.get();
            personne.setNom(personneDTO.getNom());
            personne.setDateNaissance(personneDTO.getDateNaissance());
            
            Personne updatedPersonne = personneService.save(personne);
            return ResponseEntity.ok(updatedPersonne);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        if (personneService.existsById(id)) {
            personneService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
