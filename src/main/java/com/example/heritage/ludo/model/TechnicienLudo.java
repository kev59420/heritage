package com.example.heritage.ludo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "technicienludo", schema = "heritage")
@Getter
@Setter
@NoArgsConstructor
public class TechnicienLudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom", nullable = false, length = 100)
    private String nom;

    @Column(name = "date_naissance", nullable = false)
    private LocalDate dateNaissance;

    @Column(name = "numero_employe", nullable = false, length = 20)
    private String numeroEmploye;

    @Column(name = "date_embauche", nullable = false)
    private LocalDate dateEmbauche;

    @Column(name = "specialite", nullable = false, length = 50)
    private String specialite;

    @Column(name = "niveau")
    private Integer niveau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personneludoid", nullable = false)
    private PersonneLudo personneLudo;

    public TechnicienLudo(String nom, LocalDate dateNaissance, String numeroEmploye, 
                          LocalDate dateEmbauche, String specialite, Integer niveau, 
                          PersonneLudo personneLudo) {
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
        this.specialite = specialite;
        this.niveau = niveau;
        this.personneLudo = personneLudo;
    }

    @Override
    public String toString() {
        return "TechnicienLudo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateNaissance=" + dateNaissance +
                ", numeroEmploye='" + numeroEmploye + '\'' +
                ", dateEmbauche=" + dateEmbauche +
                ", specialite='" + specialite + '\'' +
                ", niveau=" + niveau +
                ", personneLudo=" + (personneLudo != null ? personneLudo.getType() : null) +
                '}';
    }
}
