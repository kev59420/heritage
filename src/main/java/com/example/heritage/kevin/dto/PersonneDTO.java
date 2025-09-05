package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PersonneDTO {
    
    private String nom;
    private LocalDate dateNaissance;

    public PersonneDTO(String nom, LocalDate dateNaissance) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }
}
