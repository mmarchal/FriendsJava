package com.maxfriends.back.service;

import com.maxfriends.back.dto.PropositionDto;
import com.maxfriends.back.entity.EtatProposition;
import com.maxfriends.back.entity.TypeProposition;

import java.util.Collection;

public interface IPropositionService {

    Collection<PropositionDto> getAll();

    boolean addProposition(PropositionDto propositionDto);

    Collection<TypeProposition> getAllTypes();

    PropositionDto updateStatus(String idProposition, EtatProposition etatProposition);

    Collection<EtatProposition> getAllStates();
}
