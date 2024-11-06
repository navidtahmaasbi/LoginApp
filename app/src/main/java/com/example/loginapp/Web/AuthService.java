package com.example.loginapp.Web;

import com.example.loginapp.LoginRequest;
import com.example.loginapp.Models.Project;
import com.example.loginapp.Models.Task;
import com.example.loginapp.RegisterRequest;

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


    @GET("tasks")
    Call<List<Task>> getTasks(@Query("project_id") int projectId);

    @POST("tasks")
    Call<Task> createTask(@Body Task task);

    @GET("projects")
    Call<List<Project>> getProjects(@Header("Authorization") String authToken);

    @GET("projects/{id}/tasks")
    Call<List<Task>> CreateProject(@Path("id") int projectId, @Header("Authorization") String authToken);



}
