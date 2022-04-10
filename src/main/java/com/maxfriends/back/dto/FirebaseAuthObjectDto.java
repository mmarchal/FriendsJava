package com.maxfriends.back.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FirebaseAuthObjectDto {

    String kind;

    String localId;

    String email;

    String displayName;

    String idToken;

    boolean registered;

    String refreshToken;

    String expiresIn;

}
