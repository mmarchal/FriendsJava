package com.maxfriends.back.service;

import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.FriendDto;
import com.maxfriends.back.dto.SortieDto;
import com.maxfriends.back.entity.Sortie;
import com.maxfriends.back.repository.SortieRepository;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SortieService {

    private LogsInformations logsInformations = new LogsInformations();

    @Autowired
    SortieRepository sortieRepository;

    @Autowired
    GenericConverter<Sortie, SortieDto> sortieDtoGenericConverter;

    public Collection<SortieDto> getAll() {
        logsInformations.affichageLogDate("Récupération de la liste des sorties");
        return sortieDtoGenericConverter.entitiesToDtos(this.sortieRepository.findAll(), SortieDto.class);
    }

    public SortieDto loadById(String id) {
        logsInformations.affichageLogDate("Récupération des données du n°" + id);
        return this.sortieDtoGenericConverter.entityToDto(this.sortieRepository.getOne(Long.parseLong(id)), SortieDto.class);
    }
}
