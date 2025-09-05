package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.EmployeDTO;
import com.example.heritage.kevin.model.Employe;
import com.example.heritage.kevin.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/employes")
public class EmployeController {

    @Autowired
    private EmployeService employeService;

    @GetMapping
    public ResponseEntity<List<Employe>> getAllEmployes() {
        List<Employe> employes = employeService.findAll();
        return ResponseEntity.ok(employes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        Optional<Employe> employe = employeService.findById(id);
        return employe.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numeroEmploye}")
    public ResponseEntity<Employe> getEmployeByNumero(@PathVariable String numeroEmploye) {
        Optional<Employe> employe = employeService.findByNumeroEmploye(numeroEmploye);
        return employe.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Employe> createEmploye(@RequestBody EmployeDTO employeDTO) {
        Employe employe = new Employe();
        employe.setNom(employeDTO.getNom());
        employe.setDateNaissance(employeDTO.getDateNaissance());
        employe.setNumeroEmploye(employeDTO.getNumeroEmploye());
        employe.setDateEmbauche(employeDTO.getDateEmbauche());
        
        Employe savedEmploye = employeService.save(employe);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmploye);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateEmploye(@PathVariable Long id, @RequestBody EmployeDTO employeDTO) {
        Optional<Employe> existingEmploye = employeService.findById(id);
        
        if (existingEmploye.isPresent()) {
            Employe employe = existingEmploye.get();
            employe.setNom(employeDTO.getNom());
            employe.setDateNaissance(employeDTO.getDateNaissance());
            employe.setNumeroEmploye(employeDTO.getNumeroEmploye());
            employe.setDateEmbauche(employeDTO.getDateEmbauche());
            
            Employe updatedEmploye = employeService.save(employe);
            return ResponseEntity.ok(updatedEmploye);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        if (employeService.existsById(id)) {
            employeService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
