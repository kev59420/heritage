package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ManagerLudoDTO {
    
    private String nom;
    private LocalDate dateNaissance;
    private String numeroEmploye;
    private LocalDate dateEmbauche;
    private String departement;
    private BigDecimal budget;
    private Long personneLudoId;

    public ManagerLudoDTO(String nom, LocalDate dateNaissance, String numeroEmploye, 
                          LocalDate dateEmbauche, String departement, BigDecimal budget, 
                          Long personneLudoId) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
        this.departement = departement;
        this.budget = budget;
        this.personneLudoId = personneLudoId;
    }
}
