package com.maxfriends.back.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LastMessageDto {
    Long friendId;

    Long channelId;

    Long lastMessageId_read;

    LocalDateTime dateLastMessageRead;

    Long lastMessageId_sent;

    LocalDateTime dateLastMessageSent;
}
