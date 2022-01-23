package com.maxfriends.back.controller;

import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.TypeSortieDto;
import com.maxfriends.back.entity.TypeSortie;
import com.maxfriends.back.repository.TypeSortieRepository;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typesortie")
public class TypeSortieController {

    @Autowired
    TypeSortieRepository typeSortieRepository;

    @Autowired
    GenericConverter<TypeSortie, TypeSortieDto> converter;

    private LogsInformations logsInformations = new LogsInformations();

    @GetMapping
    public Collection<TypeSortieDto> getAllTypesSorties(){
        logsInformations.affichageLogDate("Récupération de la liste des types de sorties disponibles !");
        return converter.entitiesToDtos(this.typeSortieRepository.findAll(), TypeSortieDto.class);
    }
}
