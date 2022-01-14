package com.maxfriends.back.controller;

import com.maxfriends.back.dto.SortieDto;
import com.maxfriends.back.service.SortieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/sortie")
public class SortieController {

    @Autowired
    SortieService sortieService;

    @GetMapping
    public Collection<SortieDto> getAllSorties(){
        return this.sortieService.getAll();
    }

    @GetMapping("/{id}")
    public SortieDto getSortieById(@PathVariable String id) {
        return this.sortieService.loadById(id);
    }
}
