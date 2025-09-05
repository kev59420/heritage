package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.PersonneLudoDTO;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/personnes")
public class PersonneLudoController {

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<PersonneLudo>> getAllPersonnesLudo() {
        List<PersonneLudo> personnes = personneLudoService.findAll();
        return ResponseEntity.ok(personnes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonneLudo> getPersonneLudoById(@PathVariable Long id) {
        Optional<PersonneLudo> personne = personneLudoService.findById(id);
        return personne.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<PersonneLudo> getPersonneLudoByType(@PathVariable String type) {
        Optional<PersonneLudo> personne = personneLudoService.findByType(type);
        return personne.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PersonneLudo> createPersonneLudo(@RequestBody PersonneLudoDTO personneLudoDTO) {
        PersonneLudo personneLudo = new PersonneLudo();
        personneLudo.setType(personneLudoDTO.getType());
        
        PersonneLudo savedPersonneLudo = personneLudoService.save(personneLudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonneLudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonneLudo> updatePersonneLudo(@PathVariable Long id, @RequestBody PersonneLudoDTO personneLudoDTO) {
        Optional<PersonneLudo> existingPersonneLudo = personneLudoService.findById(id);
        
        if (existingPersonneLudo.isPresent()) {
            PersonneLudo personneLudo = existingPersonneLudo.get();
            personneLudo.setType(personneLudoDTO.getType());
            
            PersonneLudo updatedPersonneLudo = personneLudoService.save(personneLudo);
            return ResponseEntity.ok(updatedPersonneLudo);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonneLudo(@PathVariable Long id) {
        if (personneLudoService.existsById(id)) {
            personneLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
