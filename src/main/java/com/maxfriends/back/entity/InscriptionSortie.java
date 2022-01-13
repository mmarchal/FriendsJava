package com.maxfriends.back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "inscriptionSortie")
public class InscriptionSortie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @ManyToMany
    Collection<Sortie> sortie;

    @ManyToMany
    Collection<Friend> participant;
}
