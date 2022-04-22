package com.maxfriends.back.controller;

import com.maxfriends.back.dto.FriendDto;
import com.maxfriends.back.dto.PasswordDto;
import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.entity.Sortie;
import com.maxfriends.back.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    IFriendService friendService;

    @GetMapping
    public Collection<FriendDto> getUsers(){
        return this.friendService.getAll();
    }

    @GetMapping("/{id}")
    public FriendDto getUserById(@PathVariable String id) {
        return this.friendService.loadById(id);
    }

    @PostMapping("/create")
    public Friend createUser(@RequestBody FriendDto friendDto){
        return this.friendService.createFriend(friendDto);
    }

    @PostMapping("/{friendId}/upload/profile-image")
    public boolean uploadImageToDB(@PathVariable String friendId, @RequestBody MultipartFile imageFile) {
        return this.friendService.uploadImageToDB(imageFile, friendId);
    }

    @PutMapping
    public Friend updateUser(@RequestBody FriendDto friendDto) {
        return this.friendService.updateUser(friendDto);
    }

    @PutMapping("/resetPassword")
    public boolean resetPassword(@RequestBody PasswordDto dto) {
        return this.friendService.resetPassword(dto);
    }

    @PostMapping("/getTempPassword")
    public boolean getForgotPassword(@RequestBody String username) {
        return this.friendService.getForgotPassword(username);
    }

    @PostMapping("/checkingTempPassword/{username}")
    public boolean checkingTemp(@PathVariable String username, @RequestBody String tempString) {
        return this.friendService.checkingTempPassword(username, tempString);
    }

    @GetMapping("/{id}/sorties")
    public Collection<Sortie> getSortiesOfFriend(@PathVariable String id) {
        return this.friendService.getSortiesOfFriend(id);
    }
}
