package com.example.heritage.ludo.controller;

import com.example.heritage.ludo.dto.ClientLudoDTO;
import com.example.heritage.ludo.model.ClientLudo;
import com.example.heritage.ludo.model.PersonneLudo;
import com.example.heritage.ludo.service.ClientLudoService;
import com.example.heritage.ludo.service.PersonneLudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ludo/clients")
public class ClientLudoController {

    @Autowired
    private ClientLudoService clientLudoService;

    @Autowired
    private PersonneLudoService personneLudoService;

    @GetMapping
    public ResponseEntity<List<ClientLudo>> getAllClientsLudo() {
        List<ClientLudo> clients = clientLudoService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientLudo> getClientLudoById(@PathVariable Long id) {
        Optional<ClientLudo> client = clientLudoService.findById(id);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numeroClient}")
    public ResponseEntity<ClientLudo> getClientLudoByNumero(@PathVariable String numeroClient) {
        Optional<ClientLudo> client = clientLudoService.findByNumeroClient(numeroClient);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClientLudo> createClientLudo(@RequestBody ClientLudoDTO clientLudoDTO) {
        Optional<PersonneLudo> personneLudo = personneLudoService.findById(clientLudoDTO.getPersonneLudoId());
        
        if (personneLudo.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        ClientLudo clientLudo = new ClientLudo();
        clientLudo.setNom(clientLudoDTO.getNom());
        clientLudo.setDateNaissance(clientLudoDTO.getDateNaissance());
        clientLudo.setNumeroClient(clientLudoDTO.getNumeroClient());
        clientLudo.setDateInscription(clientLudoDTO.getDateInscription());
        clientLudo.setPersonneLudo(personneLudo.get());
        
        ClientLudo savedClientLudo = clientLudoService.save(clientLudo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClientLudo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientLudo> updateClientLudo(@PathVariable Long id, @RequestBody ClientLudoDTO clientLudoDTO) {
        Optional<ClientLudo> existingClientLudo = clientLudoService.findById(id);
        
        if (existingClientLudo.isPresent()) {
            Optional<PersonneLudo> personneLudo = personneLudoService.findById(clientLudoDTO.getPersonneLudoId());
            
            if (personneLudo.isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            ClientLudo clientLudo = existingClientLudo.get();
            clientLudo.setNom(clientLudoDTO.getNom());
            clientLudo.setDateNaissance(clientLudoDTO.getDateNaissance());
            clientLudo.setNumeroClient(clientLudoDTO.getNumeroClient());
            clientLudo.setDateInscription(clientLudoDTO.getDateInscription());
            clientLudo.setPersonneLudo(personneLudo.get());
            
            ClientLudo updatedClientLudo = clientLudoService.save(clientLudo);
            return ResponseEntity.ok(updatedClientLudo);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientLudo(@PathVariable Long id) {
        if (clientLudoService.existsById(id)) {
            clientLudoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
