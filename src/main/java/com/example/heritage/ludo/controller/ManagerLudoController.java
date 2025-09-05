package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.ManagerLudoDTO;
import com.example.heritage.ludo.model.ManagerLudo;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.ManagerLudoService;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/managers")
public class ManagerLudoController {

    @Autowired
    private ManagerLudoService managerLudoService;

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<ManagerLudo>> getAllManagersLudo() {
        List<ManagerLudo> managers = managerLudoService.findAll();
        return ResponseEntity.ok(managers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ManagerLudo> getManagerLudoById(@PathVariable Long id) {
        Optional<ManagerLudo> manager = managerLudoService.findById(id);
        return manager.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/departement/{departement}")
    public ResponseEntity<List<ManagerLudo>> getManagersLudoByDepartement(@PathVariable String departement) {
        List<ManagerLudo> managers = managerLudoService.findByDepartement(departement);
        return ResponseEntity.ok(managers);
    }

    @PostMapping
    public ResponseEntity<ManagerLudo> createManagerLudo(@RequestBody ManagerLudoDTO managerLudoDTO) {
        Optional<PersonneLudo> personneLudo = personneLudoService.findById(managerLudoDTO.getPersonneLudoId());
        
        if (personneLudo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ManagerLudo managerLudo = new ManagerLudo();
        managerLudo.setNom(managerLudoDTO.getNom());
        managerLudo.setDateNaissance(managerLudoDTO.getDateNaissance());
        managerLudo.setNumeroEmploye(managerLudoDTO.getNumeroEmploye());
        managerLudo.setDateEmbauche(managerLudoDTO.getDateEmbauche());
        managerLudo.setDepartement(managerLudoDTO.getDepartement());
        managerLudo.setBudget(managerLudoDTO.getBudget());
        managerLudo.setPersonneLudo(personneLudo.get());
        
        ManagerLudo savedManagerLudo = managerLudoService.save(managerLudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedManagerLudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ManagerLudo> updateManagerLudo(@PathVariable Long id, @RequestBody ManagerLudoDTO managerLudoDTO) {
        Optional<ManagerLudo> existingManagerLudo = managerLudoService.findById(id);
        
        if (existingManagerLudo.isPresent()) {
            Optional<PersonneLudo> personneLudo = personneLudoService.findById(managerLudoDTO.getPersonneLudoId());
            
            if (personneLudo.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            ManagerLudo managerLudo = existingManagerLudo.get();
            managerLudo.setNom(managerLudoDTO.getNom());
            managerLudo.setDateNaissance(managerLudoDTO.getDateNaissance());
            managerLudo.setNumeroEmploye(managerLudoDTO.getNumeroEmploye());
            managerLudo.setDateEmbauche(managerLudoDTO.getDateEmbauche());
            managerLudo.setDepartement(managerLudoDTO.getDepartement());
            managerLudo.setBudget(managerLudoDTO.getBudget());
            managerLudo.setPersonneLudo(personneLudo.get());
            
            ManagerLudo updatedManagerLudo = managerLudoService.save(managerLudo);
            return ResponseEntity.ok(updatedManagerLudo);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteManagerLudo(@PathVariable Long id) {
        if (managerLudoService.existsById(id)) {
            managerLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
