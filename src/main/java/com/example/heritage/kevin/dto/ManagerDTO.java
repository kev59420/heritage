package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ManagerDTO extends EmployeDTO {
    
    private String departement;
    private BigDecimal budget;

    public ManagerDTO(String nom, LocalDate dateNaissance, String numeroEmploye, 
                      LocalDate dateEmbauche, String departement, BigDecimal budget) {
        super(nom, dateNaissance, numeroEmploye, dateEmbauche);
        this.departement = departement;
        this.budget = budget;
    }
}
