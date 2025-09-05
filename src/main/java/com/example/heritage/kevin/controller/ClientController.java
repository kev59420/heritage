package com.example.heritage.kevin.controller;

import com.example.heritage.kevin.dto.ClientDTO;
import com.example.heritage.kevin.model.Client;
import com.example.heritage.kevin.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kevin/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/numero/{numeroClient}")
    public ResponseEntity<Client> getClientByNumero(@PathVariable String numeroClient) {
        Optional<Client> client = clientService.findByNumeroClient(numeroClient);
        return client.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody ClientDTO clientDTO) {
        Client client = new Client();
        client.setNom(clientDTO.getNom());
        client.setDateNaissance(clientDTO.getDateNaissance());
        client.setNumeroClient(clientDTO.getNumeroClient());
        client.setDateInscription(clientDTO.getDateInscription());
        
        Client savedClient = clientService.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
        Optional<Client> existingClient = clientService.findById(id);
        
        if (existingClient.isPresent()) {
            Client client = existingClient.get();
            client.setNom(clientDTO.getNom());
            client.setDateNaissance(clientDTO.getDateNaissance());
            client.setNumeroClient(clientDTO.getNumeroClient());
            client.setDateInscription(clientDTO.getDateInscription());
            
            Client updatedClient = clientService.save(client);
            return ResponseEntity.ok(updatedClient);
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientService.existsById(id)) {
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
