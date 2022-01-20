package com.maxfriends.back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "typesortie_id", referencedColumnName = "id")
    TypeSortie typeSortie;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "participant",
            joinColumns = {
                    @JoinColumn(name = "sortie_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "friend_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Friend> friends = new HashSet<>();
}
