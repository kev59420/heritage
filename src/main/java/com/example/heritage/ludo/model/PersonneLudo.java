package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "personne_ludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class PersonneLudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, length = 100)
    private String type;

    public PersonneLudo(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PersonneLudo{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
