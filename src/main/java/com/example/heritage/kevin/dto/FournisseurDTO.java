package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FournisseurDTO extends PersonneDTO {
    
    private String societe;
    private String contact;

    public FournisseurDTO(String nom, LocalDate dateNaissance, String societe, String contact) {
        super(nom, dateNaissance);
        this.societe = societe;
        this.contact = contact;
    }
}
