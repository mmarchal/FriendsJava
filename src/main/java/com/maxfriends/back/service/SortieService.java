package com.maxfriends.back.service;

import com.maxfriends.back.dto.SortieDto;

import java.time.LocalDateTime;
import java.util.Collection;

public interface SortieService {

    Collection<SortieDto> getAll();

    SortieDto loadById(String id);

    boolean suggestOuting(SortieDto sortieDto);

    boolean addOneFriendToOuting(Long idSortie, Long idFriend);

    SortieDto updateDateOfOuting(Long sortieId, LocalDateTime date) ;
}
