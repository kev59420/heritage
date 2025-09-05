package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TechnicienLudoDTO {
    
    private String nom;
    private LocalDate dateNaissance;
    private String numeroEmploye;
    private LocalDate dateEmbauche;
    private String specialite;
    private Integer niveau;
    private Long personneLudoId;

    public TechnicienLudoDTO(String nom, LocalDate dateNaissance, String numeroEmploye, 
                             LocalDate dateEmbauche, String specialite, Integer niveau, 
                             Long personneLudoId) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
        this.specialite = specialite;
        this.niveau = niveau;
        this.personneLudoId = personneLudoId;
    }
}
