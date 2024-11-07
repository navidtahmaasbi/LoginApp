package com.example.loginapp.Web;

import com.example.loginapp.LoginRequest;
import com.example.loginapp.models.Board;
import com.example.loginapp.models.Card;
import com.example.loginapp.models.Project;
import com.example.loginapp.models.Task;
import com.example.loginapp.RegisterRequest;
import com.example.loginapp.models.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthService {
    @POST("register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);

    @POST("login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);


    @GET("cards")
    Call<List<Task>> getTasks(@Query("project_id") int projectId);

    @POST("cards")
    Call<Task> createTask(@Body Task task);

    @GET("projects")
    Call<List<Board>> getProjects(@Header("Authorization") String authToken);

    @GET("projects/{id}/cards")
    Call<List<Card>> CreateProject(@Path("id") int projectId, @Header("Authorization") String authToken);

    @GET("user/profile")
    Call<User> getUserProfile(@Header("Authorization") String authToken);

    @POST("user/Profile")
    Call<Void> updateUserProfile(@Header("Authorization") String authToken, @Body User updatedUser);



}
