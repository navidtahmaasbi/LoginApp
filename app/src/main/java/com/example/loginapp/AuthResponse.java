package com.example.loginapp;

public class AuthResponse {
    private String accessToken;

    public String getAccessToken(){
        return accessToken;
    }
    public void setAccessToken(String accessToken){
        this.accessToken= accessToken;
    }
}
