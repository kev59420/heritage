package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FournisseurLudoDTO {
    
    private String nom;
    private LocalDate dateNaissance;
    private String societe;
    private String contact;
    private Long personneLudoId;

    public FournisseurLudoDTO(String nom, LocalDate dateNaissance, String societe, 
                              String contact, Long personneLudoId) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.societe = societe;
        this.contact = contact;
        this.personneLudoId = personneLudoId;
    }
}
