package com.example.loginapp.Web;

public class AuthResponse {
    private String accessToken;
    private int userId;

    public String getAccessToken(){
        return accessToken;
    }
    public void setAccessToken(String accessToken){
        this.accessToken= accessToken;
    }
    public int getUserId(){
        return userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
}
