package com.maxfriends.back.dto;

import com.maxfriends.back.entity.EtatProposition;
import com.maxfriends.back.entity.TypeProposition;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PropositionDto {

    Long id;

    String nom;

    TypeProposition typeProposition;

    LocalDateTime dateProposition;

    String titreDemande;

    String demande;

    EtatProposition etatProposition;

}