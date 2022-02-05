package com.maxfriends.back.dto;

import lombok.Data;

@Data
public class WebSocketMessageDto {
    private String toWhom;
    private String fromWho;
    private String message;
}
