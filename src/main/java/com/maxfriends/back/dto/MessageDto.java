package com.maxfriends.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    Long id;

    private String content;

    @JsonIgnore
    private ChannelDto channel;

    private FriendDto friend;

    private LocalDateTime deliveredAt;
}
