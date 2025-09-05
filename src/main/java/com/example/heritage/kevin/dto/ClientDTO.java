package com.example.heritage.kevin.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class ClientDTO extends PersonneDTO {
    
    private String numeroClient;
    private LocalDate dateInscription;

    public ClientDTO(String nom, LocalDate dateNaissance, String numeroClient, LocalDate dateInscription) {
        super(nom, dateNaissance);
        this.numeroClient = numeroClient;
        this.dateInscription = dateInscription;
    }
}
