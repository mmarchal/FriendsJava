package com.maxfriends.back.service;

import com.maxfriends.back.dto.ChannelDto;
import com.maxfriends.back.dto.ChannelToCreateDto;
import com.maxfriends.back.dto.MessageDto;
import com.maxfriends.back.exception.ApiException;

import java.util.Collection;

public interface IChannelService {
    Collection<ChannelDto> getLinkChannelsByFriendId(Long friendId) throws ApiException;

    boolean createManyToOneChannel(Long friendId, ChannelToCreateDto channelToCreateDto) throws ApiException;

    boolean createOneToOneChannel(Long friendId, Long friendIdToLink,String channelName) throws ApiException;

    boolean deleteChannelById(Long channelId) throws ApiException;

    boolean createMessageByChannelId(MessageDto messageDto, Long channelId) throws ApiException;

    boolean deleteMessageById(Long messageId)throws ApiException;

    Collection<MessageDto> getMessagesFromChannel(Long channelId) throws ApiException;
}
