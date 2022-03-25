package com.maxfriends.back.security.model;

public class AuthToken {

    private String token;
    private String username;
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AuthToken(){

    }

    public AuthToken(String token, String username, String userId){
        this.token = token;
        this.username = username;
        this.userId = userId;
    }

    public AuthToken(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
