package com.maxfriends.back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDto {

    private Long userId;

    private String oldPassword;

    private  String token;

    private String newPassword;
}
