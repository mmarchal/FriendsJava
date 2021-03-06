package com.maxfriends.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.entity.Sortie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class FriendDto {

    String uid;

    String prenom;

    String login;

    String password;

    String email;

    byte[] profileImage;

    boolean mdpProvisoire;

    String codeMdp;

    LocalDateTime dateExpiration;

    @JsonIgnore
    private Set<Sortie> sorties;
}
