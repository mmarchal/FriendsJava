package com.maxfriends.back.dto;

import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.entity.TypeSortie;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class SortieDto {

    Long id;

    LocalDateTime datePropose;

    String intitule;

    String lieu;

    TypeSortie typeSortie;

    private Set<Friend> friends;
}
