package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.ManagerDTO;
import com.example.heritage.kevin.model.Manager;
import com.example.heritage.kevin.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/managers")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping
    public ResponseEntity<List<Manager>> getAllManagers() {
        List<Manager> managers = managerService.findAll();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Long id) {
        Optional<Manager> manager = managerService.findById(id);
        return manager.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departement/{departement}")
    public ResponseEntity<List<Manager>> getManagersByDepartement(@PathVariable String departement) {
        List<Manager> managers = managerService.findByDepartement(departement);
        return ResponseEntity.ok(managers);
    }

    @PostMapping
    public ResponseEntity<Manager> createManager(@RequestBody ManagerDTO managerDTO) {
        Manager manager = new Manager();
        manager.setNom(managerDTO.getNom());
        manager.setDateNaissance(managerDTO.getDateNaissance());
        manager.setNumeroEmploye(managerDTO.getNumeroEmploye());
        manager.setDateEmbauche(managerDTO.getDateEmbauche());
        manager.setDepartement(managerDTO.getDepartement());
        manager.setBudget(managerDTO.getBudget());
        
        Manager savedManager = managerService.save(manager);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedManager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manager> updateManager(@PathVariable Long id, @RequestBody ManagerDTO managerDTO) {
        Optional<Manager> existingManager = managerService.findById(id);
        
        if (existingManager.isPresent()) {
            Manager manager = existingManager.get();
            manager.setNom(managerDTO.getNom());
            manager.setDateNaissance(managerDTO.getDateNaissance());
            manager.setNumeroEmploye(managerDTO.getNumeroEmploye());
            manager.setDateEmbauche(managerDTO.getDateEmbauche());
            manager.setDepartement(managerDTO.getDepartement());
            manager.setBudget(managerDTO.getBudget());
            
            Manager updatedManager = managerService.save(manager);
            return ResponseEntity.ok(updatedManager);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        if (managerService.existsById(id)) {
            managerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
