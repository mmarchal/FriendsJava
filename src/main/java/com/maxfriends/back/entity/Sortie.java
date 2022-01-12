package com.maxfriends.back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sortie")
public class Sortie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column
    LocalDateTime datePropose;

    @Column
    String intitule;

    @Column
    String lieu;

    @OneToMany(mappedBy="sortie")
    private Set<Friend> friends;


}
