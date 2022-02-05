package com.maxfriends.back.service.impl;

import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.SortieDto;
import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.entity.Sortie;
import com.maxfriends.back.repository.FriendRepository;
import com.maxfriends.back.repository.SortieRepository;
import com.maxfriends.back.repository.TypeSortieRepository;
import com.maxfriends.back.service.SortieService;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Optional;

@Service
public class SortieServiceImpl implements SortieService {

    private LogsInformations logsInformations = new LogsInformations();

    @Autowired
    SortieRepository sortieRepository;

    @Autowired
    FriendRepository friendRepository;

    @Autowired
    TypeSortieRepository typeSortieRepository;

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

    public boolean suggestOuting(SortieDto sortieDto) {

        try {
            Sortie sortie = sortieDtoGenericConverter.dtoToEntity(sortieDto, Sortie.class);
            sortie.setTypeSortie(typeSortieRepository.getOne(sortieDto.getTypeSortie().getId()));
            this.sortieRepository.save(sortie);
            logsInformations.affichageLogDate("Création de la sortie : " + sortieDto.getIntitule());
            return true;
        } catch (Exception e) {
            logsInformations.affichageLogDate("Erreur lors de la création de la sortie " + sortieDto.getIntitule() + " : " + e.getMessage());
            return false;
        }

    }

    public boolean addOneFriendToOuting(Long idSortie, Long idFriend) {
        Optional<Friend> friend = friendRepository.findById(idFriend);
        Optional<Sortie> sortie = sortieRepository.findById(idSortie);

        if(friend.isPresent() && sortie.isPresent()) {
            sortie.get().getFriends().add(friend.get());
            this.sortieRepository.save(sortie.get());
            logsInformations.affichageLogDate(friend.get().getPrenom() + " inscrit à la sortie " + sortie.get().getIntitule());
            return true;
        } else if (!friend.isPresent()) {
            logsInformations.affichageLogDate("Ami avec id " + idFriend + " non trouvé !");
            return false;
        } else {
            logsInformations.affichageLogDate("Sortie avec id " + idSortie + " non trouvé !");
            return false;
        }
    }

    public SortieDto updateDateOfOuting(Long sortieId, LocalDateTime date) {
        Optional<Sortie> sortie = sortieRepository.findById(sortieId);
        if(sortie.isPresent()) {
            Sortie sortieTrouve = sortie.get();
            sortieTrouve.setDatePropose(date);
            this.sortieRepository.save(sortieTrouve);
            logsInformations.affichageLogDate("Sortie " + sortieTrouve.getIntitule() + " avec nouvelle date : " + sortieTrouve.getDatePropose().format(DateTimeFormatter.ISO_LOCAL_DATE)  + " !");
            return sortieDtoGenericConverter.entityToDto(sortieTrouve, SortieDto.class);
        } else {
            logsInformations.affichageLogDate("Sortie avec id " + sortieId + " non trouvé !");
            return null;
        }
    }
}
