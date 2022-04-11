package com.maxfriends.back.entity;

import com.google.api.client.util.Value;

public class FirebaseConfiguration {

    @Value("${firebase.configuration.firebaseLoginUrl}")
    public String firebaseLoginUrl;
    @Value("${firebase.configuration.firebaseLoginKey}")
    public String firebaseLoginKey;
    @Value("${firebase.configuration.firebaseLoginReturnSecureToken}")
    public boolean firebaseLoginReturnSecureToken;
}
