package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.EmployeLudoDTO;
import com.example.heritage.ludo.model.EmployeLudo;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.EmployeLudoService;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/employes")
public class EmployeLudoController {

    @Autowired
    private EmployeLudoService employeLudoService;

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<EmployeLudo>> getAllEmployesLudo() {
        List<EmployeLudo> employes = employeLudoService.findAll();
        return ResponseEntity.ok(employes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeLudo> getEmployeLudoById(@PathVariable Long id) {
        Optional<EmployeLudo> employe = employeLudoService.findById(id);
        return employe.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numeroEmploye}")
    public ResponseEntity<EmployeLudo> getEmployeLudoByNumero(@PathVariable String numeroEmploye) {
        Optional<EmployeLudo> employe = employeLudoService.findByNumeroEmploye(numeroEmploye);
        return employe.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeLudo> createEmployeLudo(@RequestBody EmployeLudoDTO employeLudoDTO) {
        Optional<PersonneLudo> personneLudo = personneLudoService.findById(employeLudoDTO.getPersonneLudoId());
        
        if (personneLudo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        EmployeLudo employeLudo = new EmployeLudo();
        employeLudo.setNom(employeLudoDTO.getNom());
        employeLudo.setDateNaissance(employeLudoDTO.getDateNaissance());
        employeLudo.setNumeroEmploye(employeLudoDTO.getNumeroEmploye());
        employeLudo.setDateEmbauche(employeLudoDTO.getDateEmbauche());
        employeLudo.setPersonneLudo(personneLudo.get());
        
        EmployeLudo savedEmployeLudo = employeLudoService.save(employeLudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployeLudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeLudo> updateEmployeLudo(@PathVariable Long id, @RequestBody EmployeLudoDTO employeLudoDTO) {
        Optional<EmployeLudo> existingEmployeLudo = employeLudoService.findById(id);
        
        if (existingEmployeLudo.isPresent()) {
            Optional<PersonneLudo> personneLudo = personneLudoService.findById(employeLudoDTO.getPersonneLudoId());
            
            if (personneLudo.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            EmployeLudo employeLudo = existingEmployeLudo.get();
            employeLudo.setNom(employeLudoDTO.getNom());
            employeLudo.setDateNaissance(employeLudoDTO.getDateNaissance());
            employeLudo.setNumeroEmploye(employeLudoDTO.getNumeroEmploye());
            employeLudo.setDateEmbauche(employeLudoDTO.getDateEmbauche());
            employeLudo.setPersonneLudo(personneLudo.get());
            
            EmployeLudo updatedEmployeLudo = employeLudoService.save(employeLudo);
            return ResponseEntity.ok(updatedEmployeLudo);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployeLudo(@PathVariable Long id) {
        if (employeLudoService.existsById(id)) {
            employeLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
