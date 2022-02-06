package com.maxfriends.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "friend")
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column
    String prenom;

    @Column
    String login;

    @Column
    String password;

    @Column
    String email;

    @Column(columnDefinition = "boolean default false")
    boolean mdpProvisoire;

    @Column
    String codeMdp;

    @Column
    LocalDateTime dateExpiration;

    @ManyToMany(mappedBy = "friends")
    private Collection<Channel> channels;

    @OneToMany(mappedBy = "friend")
    private Collection<Message> message;

    @JsonIgnore
    @ManyToMany(mappedBy = "friends", fetch = FetchType.LAZY)
    private Set<Sortie> sorties = new HashSet<>();


}

