package com.maxfriends.back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "proposition_evaluation")
public class Proposition  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column
    String nom;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "typeProposition_id", referencedColumnName = "id")
    TypeProposition typeProposition;

    @Column
    LocalDateTime dateProposition;

    @Column
    String titreDemande;

    @Column
    String demande;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "etatProposition_id", referencedColumnName = "id")
    EtatProposition etatProposition;
}
