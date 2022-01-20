package com.maxfriends.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maxfriends.back.entity.Sortie;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeSortieDto {

    Long id;

    String type;

    @JsonIgnore
    Sortie sortie;
}
