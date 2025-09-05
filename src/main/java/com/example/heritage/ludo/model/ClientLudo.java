package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "clientludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class ClientLudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "numero_client", nullable = false, length = 20)
    private String numeroClient;

    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personneludoid", nullable = false)
    private PersonneLudo personneLudo;

    public ClientLudo(String nom, LocalDate dateNaissance, String numeroClient, 
                      LocalDate dateInscription, PersonneLudo personneLudo) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroClient = numeroClient;
        this.dateInscription = dateInscription;
        this.personneLudo = personneLudo;
    }

    @Override
    public String toString() {
        return "ClientLudo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", numeroClient='" + numeroClient + '\'' +
                ", dateInscription=" + dateInscription +
                ", personneLudo=" + (personneLudo != null ? personneLudo.getType() : null) +
                '}';
    }
}
