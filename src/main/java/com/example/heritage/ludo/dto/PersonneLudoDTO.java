package com.example.heritage.ludo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PersonneLudoDTO {
    
    private String type;

    public PersonneLudoDTO(String type) {
        this.type = type;
    }
}
