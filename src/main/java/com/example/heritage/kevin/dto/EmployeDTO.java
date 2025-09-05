package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeDTO extends PersonneDTO {
    
    private String numeroEmploye;
    private LocalDate dateEmbauche;

    public EmployeDTO(String nom, LocalDate dateNaissance, String numeroEmploye, LocalDate dateEmbauche) {
        super(nom, dateNaissance);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }
}
