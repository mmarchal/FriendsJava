package com.maxfriends.back.service.impl;

import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.LastMessageDto;
import com.maxfriends.back.entity.Channel;
import com.maxfriends.back.entity.LastMessage;
import com.maxfriends.back.exception.ApiException;
import com.maxfriends.back.repository.ChannelRepository;
import com.maxfriends.back.repository.FriendRepository;
import com.maxfriends.back.repository.LastMessageRepository;
import com.maxfriends.back.repository.MessageRepository;
import com.maxfriends.back.service.ILastMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LastMessageServiceImpl implements ILastMessageService {

    @Autowired
    private LastMessageRepository lastMessageRepo;

    @Autowired
    private FriendRepository friendRepo;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private ChannelRepository channelRepo;

    @Autowired
    private GenericConverter<LastMessage, LastMessageDto> lastMessageConverter;

    @Override
    public LastMessageDto getLastMessagesByIdFriendAndChannel(Long channelId, Long friendId) throws ApiException {
        if(!this.channelRepo.existsById(channelId)){
            throw new ApiException("Aucun channel avec cet id n'existe !", HttpStatus.NOT_FOUND);
        }
        if(!this.friendRepo.existsById(friendId)){
            throw new ApiException("Aucun ami avec cet id n'existe !", HttpStatus.NOT_FOUND);
        }

        return this.lastMessageConverter.entityToDto(this.lastMessageRepo.findByFriendIdAndChannelId(friendId,channelId),LastMessageDto.class);
    }

    @Override
    public boolean isUpToDate(Long channelId, Long friendId) throws ApiException {
        if(!this.channelRepo.existsById(channelId)){
            throw new ApiException("Aucun channel avec cet id n'existe !", HttpStatus.NOT_FOUND);
        }
        if(!this.friendRepo.existsById(friendId)){
            throw new ApiException("Aucun ami avec cet id n'existe !", HttpStatus.NOT_FOUND);
        }

        LastMessage lastMessage = this.lastMessageRepo.findByFriendIdAndChannelId(friendId,channelId);

        if(lastMessage == null || lastMessage.getDateLastMessageRead() == null){
            return false;
        }

        if(lastMessage.getDateLastMessageRead().isBefore(this.messageRepo.getLastMessageSentInChannel(channelId))){
            return false;
        }

        return true;
    }
}
