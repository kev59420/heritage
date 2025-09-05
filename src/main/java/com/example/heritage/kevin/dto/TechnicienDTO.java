package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TechnicienDTO extends EmployeDTO {
    
    private String specialite;
    private Integer niveau;

    public TechnicienDTO(String nom, LocalDate dateNaissance, String numeroEmploye, 
                         LocalDate dateEmbauche, String specialite, Integer niveau) {
        super(nom, dateNaissance, numeroEmploye, dateEmbauche);
        this.specialite = specialite;
        this.niveau = niveau;
    }
}
