package com.maxfriends.back.dto;

import com.maxfriends.back.entity.Friend;
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

    private Set<Friend> friends;
}
