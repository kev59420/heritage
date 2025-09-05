package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeLudoDTO {
    
    private String nom;
    private LocalDate dateNaissance;
    private String numeroEmploye;
    private LocalDate dateEmbauche;
    private Long personneLudoId;

    public EmployeLudoDTO(String nom, LocalDate dateNaissance, String numeroEmploye, 
                          LocalDate dateEmbauche, Long personneLudoId) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
        this.personneLudoId = personneLudoId;
    }
}
