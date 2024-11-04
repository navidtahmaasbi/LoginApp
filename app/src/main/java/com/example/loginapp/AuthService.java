package com.example.loginapp;

import com.example.loginapp.AuthResponse;
import com.example.loginapp.RegisterRequest;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface AuthService {
    @POST("register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);
}
