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

    public FirebaseAuthObjectDto(String kind, String localId, String email, String displayName, String idToken, boolean registered, String refreshToken, String expiresIn) {
        this.kind = kind;
        this.localId = localId;
        this.email = email;
        this.displayName = displayName;
        this.idToken = idToken;
        this.registered = registered;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
    }
}
