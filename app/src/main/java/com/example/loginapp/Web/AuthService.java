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

    // Board Routes
    @GET("users/{userId}/boards")
    Call<List<Board>> getBoards(@Path("userId") int userId, @Header("Authorization") String authToken);

    @POST("users/{userId}/boards")
    Call<Board> createBoard(@Path("userId") int userId, @Body Board board, @Header("Authorization") String authToken);

    @GET("boards/{boardId}")
    Call<Board> getBoard(@Path("boardId") int boardId, @Header("Authorization") String authToken);

    @PUT("boards/{boardId}")
    Call<Board> updateBoard(@Path("boardId") int boardId, @Body Board board, @Header("Authorization") String authToken);

    @DELETE("boards/{boardId}")
    Call<Void> deleteBoard(@Path("boardId") int boardId, @Header("Authorization") String authToken);

    // Category Routes
    @GET("boards/{boardId}/categories")
    Call<List<Category>> getCategories(@Path("boardId") int boardId, @Header("Authorization") String authToken);

    @POST("boards/{boardId}/categories")
    Call<Category> createCategory(@Path("boardId") int boardId, @Body Category category, @Header("Authorization") String authToken);

    @GET("boards/{boardId}/categories/{categoryId}")
    Call<Category> getCategory(@Path("boardId") int boardId, @Path("categoryId") int categoryId, @Header("Authorization") String authToken);

    @PUT("boards/{boardId}/categories/{categoryId}")
    Call<Category> updateCategory(@Path("boardId") int boardId, @Path("categoryId") int categoryId, @Body Category category, @Header("Authorization") String authToken);

    @DELETE("boards/{boardId}/categories/{categoryId}")
    Call<Void> deleteCategory(@Path("boardId") int boardId, @Path("categoryId") int categoryId, @Header("Authorization") String authToken);

    // Card Routes
    @GET("categories/{categoryId}/cards")
    Call<List<Card>> getCards(@Path("categoryId") int categoryId, @Header("Authorization") String authToken);

    @POST("categories/{categoryId}/cards")
    Call<Card> createCard(@Path("categoryId") int categoryId, @Body Card card, @Header("Authorization") String authToken);

    @GET("categories/{categoryId}/cards/{cardId}")
    Call<Card> getCard(@Path("categoryId") int categoryId, @Path("cardId") int cardId, @Header("Authorization") String authToken);

    @PUT("categories/{categoryId}/cards/{cardId}")
    Call<Card> updateCard(@Path("categoryId") int categoryId, @Path("cardId") int cardId, @Body Card card, @Header("Authorization") String authToken);

    @DELETE("categories/{categoryId}/cards/{cardId}")
    Call<Void> deleteCard(@Path("categoryId") int categoryId, @Path("cardId") int cardId, @Header("Authorization") String authToken);

    // User Profile Routes
    @GET("user/profile")
    Call<User> getUserProfile(@Header("Authorization") String authToken);

    @POST("user/profile")
    Call<Void> updateUserProfile(@Header("Authorization") String authToken, @Body User updatedUser);
}
