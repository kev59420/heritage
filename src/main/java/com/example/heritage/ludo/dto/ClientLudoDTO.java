package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClientLudoDTO {
    
    private String nom;
    private LocalDate dateNaissance;
    private String numeroClient;
    private LocalDate dateInscription;
    private Long personneLudoId;

    public ClientLudoDTO(String nom, LocalDate dateNaissance, String numeroClient, 
                         LocalDate dateInscription, Long personneLudoId) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroClient = numeroClient;
        this.dateInscription = dateInscription;
        this.personneLudoId = personneLudoId;
    }
}
