package com.maxfriends.back.controller;

import com.maxfriends.back.dto.LastMessageDto;
import com.maxfriends.back.exception.ApiException;
import com.maxfriends.back.service.ILastMessageService;
import com.maxfriends.back.service.impl.LastMessageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lastMessage")
public class LastMessageController {

    @Autowired
    private ILastMessageService lastMessageService;

    @GetMapping("/check/{channelId}/{friendId}")
    public LastMessageDto getLastMessagesByIdFriendAndChannel(@PathVariable("channelId")Long channelId,@PathVariable("friendId")Long friendId) throws ApiException {
        if(friendId == null || friendId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        if(channelId == null || channelId == 0){
            throw new ApiException("channelId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.lastMessageService.getLastMessagesByIdFriendAndChannel(channelId,friendId);
    }
    @GetMapping("/{channelId}/{friendId}")
    public boolean isUpToDate(@PathVariable("channelId")Long channelId,@PathVariable("friendId")Long friendId) throws ApiException {
        if(friendId == null || friendId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        if(channelId == null || channelId == 0){
            throw new ApiException("channelId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.lastMessageService.isUpToDate(channelId,friendId);
    }
}
