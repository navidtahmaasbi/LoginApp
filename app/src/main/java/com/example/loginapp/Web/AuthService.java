package com.example.loginapp.Web;

import com.example.loginapp.LoginRequest;
import com.example.loginapp.models.Board;
import com.example.loginapp.models.Card;
import com.example.loginapp.models.Category;
import com.example.loginapp.models.Project;
import com.example.loginapp.models.Task;
import com.example.loginapp.RegisterRequest;
import com.example.loginapp.models.User;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AuthService {
    @POST("register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);

    @POST("login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @GET("users/{id}/boards")
    Call<List<Board>> getBoards(@Path("id") int userId, @Header("Authorization") String authToken);

    @POST("users/{id}/boards")
    Call<Board> createBoard(@Path("id") int userId, @Body Board board, @Header("Authorization") String authToken);

    @GET("boards/{id}")
    Call<Board> getBoard(@Path("id") int boardId, @Header("Authorization") String authToken);

    @PUT("boards/{id}")
    Call<Board> updateBoard(@Path("id") int boardId, @Body Board board, @Header("Authorization") String authToken);

    @DELETE("boards/{id}")
    Call<Void> deleteBoard(@Path("id") int boardId, @Header("Authorization") String authToken);

    // Category Routes
    @GET("boards/{id}/categories")
    Call<List<Category>> getCategories(@Path("id") int boardId, @Header("Authorization") String authToken);

    @POST("boards/{id}/categories")
    Call<Category> createCategory(@Path("id") int boardId, @Body Category category, @Header("Authorization") String authToken);

    @GET("boards/{id}/categories/{id}")
    Call<Category> getCategory(@Path("id") int boardId, @Path("id") int categoryId, @Header("Authorization") String authToken);

    @PUT("boards/{id}/categories/{id}")
    Call<Category> updateCategory(@Path("id") int boardId, @Path("id") int categoryId, @Body Category category, @Header("Authorization") String authToken);

    @DELETE("boards/{id}/categories/{id}")
    Call<Void> deleteCategory(@Path("id") int boardId, @Path("id") int categoryId, @Header("Authorization") String authToken);

    // Card Routes
    @GET("categories/{id}/cards")
    Call<List<Card>> getCards(@Path("id") int categoryId, @Header("Authorization") String authToken);

    @POST("categories/{id}/cards")
    Call<Card> createCard(@Path("id") int categoryId, @Body Card card, @Header("Authorization") String authToken);

    @GET("categories/{id}/cards/{id}")
    Call<Card> getCard(@Path("id") int categoryId, @Path("id") int cardId, @Header("Authorization") String authToken);

    @PUT("categories/{id}/cards/{id}")
    Call<Card> updateCard(@Path("id") int categoryId, @Path("id") int cardId, @Body Card card, @Header("Authorization") String authToken);

    @DELETE("categories/{id}/cards/{id}")
    Call<Void> deleteCard(@Path("id") int categoryId, @Path("id") int cardId, @Header("Authorization") String authToken);

    @GET("user/profile")
    Call<User> getUserProfile(@Header("Authorization") String authToken);

    @POST("user/Profile")
    Call<Void> updateUserProfile(@Header("Authorization") String authToken, @Body User updatedUser);



}
