package com.maxfriends.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxfriends.back.entity.Sortie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FriendDto {

    Long id;

    String prenom;

    String login;

    String password;

    String email;

    boolean mdpProvisoire;

    String codeMdp;

    LocalDateTime dateExpiration;

    @JsonIgnore
    private Sortie sortie;
}