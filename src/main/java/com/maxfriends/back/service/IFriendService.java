package com.maxfriends.back.service;

import com.maxfriends.back.dto.FriendDto;
import com.maxfriends.back.dto.PasswordDto;
import com.maxfriends.back.entity.Sortie;

import java.util.Collection;

public interface IFriendService {

    Collection<FriendDto> getAll();

    FriendDto loadById(String id);

    boolean resetPassword(PasswordDto dto);

    boolean checkingTempPassword(String username, String tempString);

    boolean getForgotPassword(String u);

    Collection<Sortie> getSortiesOfFriend(Long id);

    boolean createFriend(FriendDto friendDto);
}
