package com.maxfriends.back.dto;

import lombok.Data;

import java.util.Collection;

@Data
public class ChannelToCreateDto {

    private String name;
    private Collection<Long> friendIdsToLink;
}
