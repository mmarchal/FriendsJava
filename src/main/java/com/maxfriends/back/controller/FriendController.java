package com.maxfriends.back.controller;

import com.maxfriends.back.dto.FriendDto;
import com.maxfriends.back.entity.Sortie;
import com.maxfriends.back.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping
    public Collection<FriendDto> getUsers(){
        return this.friendService.getAll();
    }

    @GetMapping("/{id}")
    public FriendDto getUserById(@PathVariable String id) {
        return this.friendService.loadById(id);
    }

    @PostMapping
    public boolean createUser(@RequestBody FriendDto friendDto){
        return this.friendService.createFriend(friendDto);
    }

    @PutMapping("/resetPassword")
    public boolean resetPasswordId(@RequestBody FriendDto dto) {
        return this.friendService.resetPassword(dto);
    }

    @PostMapping("/getTempPassword")
    public boolean getPassword(@RequestBody String username) {
        return this.friendService.getForgotPassword(username);
    }

    @PostMapping("/checkingTempPassword/{username}")
    public boolean checkingTemp(@PathVariable String username, @RequestBody String tempString) {
        return this.friendService.checkingTempPassword(username, tempString);
    }

    @GetMapping("/{id}/sorties")
    public Collection<Sortie> getSortiesOfFriend(@PathVariable Long id) {
        return this.friendService.getSortiesOfFriend(id);
    }
}
