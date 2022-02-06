package com.maxfriends.back.service.impl;

import com.maxfriends.back.converter.GenericConverter;
import com.maxfriends.back.dto.ChannelDto;
import com.maxfriends.back.dto.ChannelToCreateDto;
import com.maxfriends.back.dto.MessageDto;
import com.maxfriends.back.entity.Channel;
import com.maxfriends.back.entity.Friend;
import com.maxfriends.back.entity.Message;
import com.maxfriends.back.exception.ApiException;
import com.maxfriends.back.repository.ChannelRepository;
import com.maxfriends.back.repository.FriendRepository;
import com.maxfriends.back.repository.MessageRepository;
import com.maxfriends.back.service.IChannelService;
import com.maxfriends.back.utilities.LogsInformations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelServiceImpl implements IChannelService {

    private LogsInformations logsInformations = new LogsInformations();

    @Autowired
    private ChannelRepository channelRepo;

    @Autowired
    private FriendRepository friendRepo;

    @Autowired
    private MessageRepository messageRepo;

    @Autowired
    private GenericConverter<Message,MessageDto> messageConverter;

    @Autowired
    private GenericConverter<Channel,ChannelDto> channelConverter;

    @Override
    public Collection<ChannelDto> getLinkChannelsByFriendId(Long friendId) throws ApiException {
        logsInformations.affichageLogDate("Récupération de la liste des channels pour l'id : "+friendId);
        return this.channelConverter.entitiesToDtos(this.channelRepo.getChannelByFriendsId(friendId),ChannelDto.class);
    }

    @Override
    public boolean createManyToOneChannel(Long friendId, ChannelToCreateDto channelToCreateDto) throws ApiException {
        Friend friend = this.friendRepo.getOne(friendId);
        if(friend == null){
            throw new ApiException("Aucun ami avec l'id : "+friendId + " trouvé !", HttpStatus.NOT_FOUND);
        }

        Channel channelToCreate = new Channel();
        channelToCreate.setName(channelToCreateDto.getName());
        channelToCreate.setFriends(channelToCreateDto.getFriendIdsToLink().stream().map(id->this.friendRepo.getOne(id)).collect(Collectors.toList()));
        this.channelRepo.save(channelToCreate);
        return true;
    }

    @Override
    public boolean createOneToOneChannel(Long friendId, Long friendIdToLink,String channelName) throws ApiException {
        Friend firstFriendToLink = this.friendRepo.getOne(friendId);
        Friend secondFriendToLink = this.friendRepo.getOne(friendIdToLink);

        if(firstFriendToLink == null){
            throw new ApiException("Aucun ami avec l'id : "+friendId + " trouvé !", HttpStatus.NOT_FOUND);
        }
        if(secondFriendToLink == null){
            throw new ApiException("Aucun ami avec l'id : "+friendIdToLink + " trouvé !", HttpStatus.NOT_FOUND);
        }

        if(this.existsByFriendsIds(friendId,friendIdToLink)){
            throw new ApiException("Un channel existe déjà entre ces deux amis !", HttpStatus.CONFLICT);
        }

        Channel channel = new Channel();

        channel.setName(channelName);
        Collection<Friend> friends = new ArrayList<>();
        friends.add(this.friendRepo.getOne(friendId));
        friends.add(this.friendRepo.getOne(friendIdToLink));
        channel.setFriends(friends);

        this.channelRepo.save(channel);

        return true;
    }

    // Méthode qui permet de savoir si un channel existe déjà entre les deux personnes faisable en SQL mais flemme
    private boolean existsByFriendsIds(Long friendId,Long friendIdToLink){
        Collection<Channel> channels = this.channelRepo.findAll();
        boolean exists = false;

        for(Channel channel : channels){
            if(channel.getFriends()!= null && channel.getFriends().size()>0){
                List<Long> friendsIds = channel.getFriends().stream().map(friend->friend.getId()).collect(Collectors.toList());
                if(friendsIds!= null && friendsIds.size() == 2 && friendsIds.contains(friendId) && friendsIds.contains(friendIdToLink)){
                    exists = true;
                    break;
                }
            }

        }

        return exists;
    }

    @Override
    public boolean deleteChannelById(Long channelId) throws ApiException {
        if(!this.channelRepo.existsById(channelId)){
            throw new ApiException("Aucun channel avec l'id "+channelId+" n'existe !", HttpStatus.NOT_FOUND);
        }
        this.channelRepo.deleteById(channelId);
        return true;
    }

    @Override
    public boolean createMessageByChannelId(MessageDto messageDto, Long channelId) throws ApiException {
        if(!this.channelRepo.existsById(channelId)){
            throw new ApiException("Aucun channel avec l'id "+channelId+" n'existe !", HttpStatus.NOT_FOUND);
        }

        Channel channel = this.channelRepo.getOne(channelId);

        Message message = this.messageConverter.dtoToEntity(messageDto,Message.class);
        message.setChannel(channel);
        message.setDeliveredAt(LocalDateTime.now());

        if(channel.getMessagesList()!= null ) {
            channel.getMessagesList().add(this.messageRepo.save(message));
        }else{
            channel.setMessagesList(Arrays.asList(this.messageRepo.save(message)));
        }

        return true;
    }

    @Override
    public boolean deleteMessageById(Long messageId) throws ApiException {
        if(!this.messageRepo.existsById(messageId)){
            throw new ApiException("Aucun channel avec l'id "+messageId+" n'existe !", HttpStatus.NOT_FOUND);
        }
        this.messageRepo.deleteById(messageId);
        return true;
    }
}
