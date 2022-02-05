package com.maxfriends.back.service.impl;


import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.PropositionDto;
import com.maxfriends.back.entity.Proposition;
import com.maxfriends.back.entity.TypeProposition;
import com.maxfriends.back.repository.PropositionRepository;
import com.maxfriends.back.repository.TypePropositionRepository;
import com.maxfriends.back.service.PropositionService;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PropositionServiceImpl implements PropositionService {

    private LogsInformations logsInformations = new LogsInformations();

    @Autowired
    private
    PropositionRepository propositionRepository;

    @Autowired
    private
    TypePropositionRepository typePropositionRepository;

    @Autowired
    private
    GenericConverter<Proposition, PropositionDto> propositionDtoGenericConverter;

    public Collection<PropositionDto> getAll() {
        logsInformations.affichageLogDate("Récupération de la liste des propositions");
        return propositionDtoGenericConverter.entitiesToDtos(propositionRepository.findAll(), PropositionDto.class);
    }

    public boolean addProposition(PropositionDto propositionDto) {
        try {
            Proposition proposition = propositionDtoGenericConverter.dtoToEntity(propositionDto, Proposition.class);
            proposition.setTypeProposition(typePropositionRepository.getOne(propositionDto.getTypeProposition().getId()));
            this.propositionRepository.save(proposition);
            logsInformations.affichageLogDate("Création de la proposition : " + propositionDto.getDemande());
            return true;
        } catch (Exception e) {
            logsInformations.affichageLogDate("Erreur lors de la création de la proposition " + propositionDto.getDemande() + " : " + e.getMessage());
            return false;
        }
    }

    public Collection<TypeProposition> getAllTypes() {
        logsInformations.affichageLogDate("Récupération de la liste des types de propositions");
        return typePropositionRepository.findAll();
    }
}
