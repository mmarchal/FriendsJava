package com.maxfriends.back.controller;


import com.maxfriends.back.dto.PropositionDto;
import com.maxfriends.back.entity.EtatProposition;
import com.maxfriends.back.entity.TypeProposition;
import com.maxfriends.back.service.impl.PropositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/proposition")
public class PropositionController {

    @Autowired
    PropositionServiceImpl propositionService;


    @GetMapping
    public Collection<PropositionDto> getPropositions(){
        return this.propositionService.getAll();
    }

    @PostMapping
    public boolean addProposition(@RequestBody PropositionDto propositionDto){
        return this.propositionService.addProposition(propositionDto);
    }

    @GetMapping("/types")
    public Collection<TypeProposition> getTypesPropositions(){
        return this.propositionService.getAllTypes();
    }

    @PutMapping("/{idProposition}/updateStatus")
    public PropositionDto updateStatusOfProposition(@PathVariable String idProposition, @RequestBody EtatProposition etatProposition) {
        return this.propositionService.updateStatus(idProposition, etatProposition);
    }

    @GetMapping("/etats")
    public Collection<EtatProposition> getAllStates() {
        return this.propositionService.getAllStates();
    }
}
