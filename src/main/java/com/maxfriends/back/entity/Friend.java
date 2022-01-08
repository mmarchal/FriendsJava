package com.maxfriends.back.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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


}

