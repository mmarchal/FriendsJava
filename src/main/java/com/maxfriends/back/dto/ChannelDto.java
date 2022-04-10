package com.maxfriends.back.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Collection;

@Data
public class ChannelDto {
    private Long id;

    private String name;

    @JsonIgnore
    private Collection<FriendDto> friends;

    private Collection<MessageDto> messagesList;
}
