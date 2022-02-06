package com.maxfriends.back.controller;


import com.maxfriends.back.dto.ChannelDto;
import com.maxfriends.back.dto.ChannelToCreateDto;
import com.maxfriends.back.dto.MessageDto;
import com.maxfriends.back.exception.ApiException;
import com.maxfriends.back.service.IChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chat")
public class ChannelController {

    @Autowired
    private IChannelService channelService;

    @GetMapping("/{friendId}")
    public Collection<ChannelDto> getLinkChannelsByFriendId(@PathVariable(name="friendId") Long friendId) throws ApiException {
        if(friendId == null || friendId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.channelService.getLinkChannelsByFriendId(friendId);
    }

    @PostMapping("/{friendId}/{friendIdToLink}/{channelName}")
    public boolean createOneToOneChannel(@PathVariable(name="friendId") Long friendId,@PathVariable(name="friendIdToLink") Long friendIdToLink,
                                         @PathVariable(name="channelName") String channelName) throws ApiException{
        if(friendId == null || friendId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        if(friendIdToLink == null || friendIdToLink == 0){
            throw new ApiException("friendIdToLink cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.channelService.createOneToOneChannel(friendId,friendIdToLink,channelName);
    }

    @PostMapping("/{friendId}")
    public boolean createManyToOneChannel(@PathVariable(name="friendId") Long friendId,@RequestBody ChannelToCreateDto channelToCreateDto) throws ApiException{
        if(friendId == null || friendId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        if(channelToCreateDto == null || channelToCreateDto.getFriendIdsToLink() == null || channelToCreateDto.getFriendIdsToLink().size() == 0){
            throw new ApiException("channel to create cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.channelService.createManyToOneChannel(friendId,channelToCreateDto);
    }

    @PostMapping("/message/{channelId}")
    public boolean createMessageByChannelId(@RequestBody MessageDto messageDto,@PathVariable(name="channelId") Long channelId) throws ApiException{
        if(channelId == null || channelId == 0){
            throw new ApiException("channelId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        if(messageDto == null || messageDto.getFriend() == null || messageDto.getContent() == null){
            throw new ApiException("message cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.channelService.createMessageByChannelId(messageDto,channelId);
    }

    @DeleteMapping("/{channelId}")
    public boolean deleteChannelById(@PathVariable(name="channelId") Long channelId)throws ApiException{
        if(channelId == null || channelId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.channelService.deleteChannelById(channelId);
    }

    @DeleteMapping("/message/{messageId}")
    public boolean deleteMessageById(@PathVariable(name="messageId") Long messageId)throws ApiException{
        if(messageId == null || messageId == 0){
            throw new ApiException("friendId cannot be empty !", HttpStatus.NO_CONTENT);
        }
        return this.channelService.deleteMessageById(messageId);
    }

}
