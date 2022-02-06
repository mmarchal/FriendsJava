package com.maxfriends.back.service;

import com.maxfriends.back.dto.LastMessageDto;
import com.maxfriends.back.exception.ApiException;

public interface ILastMessageService {
    LastMessageDto getLastMessagesByIdFriendAndChannel(Long channelId, Long friendId) throws ApiException;

    boolean isUpToDate(Long channelId, Long friendId) throws ApiException;
}
