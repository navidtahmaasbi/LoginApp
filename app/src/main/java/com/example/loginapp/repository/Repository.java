package com.example.loginapp.repository;

import androidx.annotation.NonNull;

import com.example.loginapp.BoardRequest;
import com.example.loginapp.Web.ApiClient;
import com.example.loginapp.Web.AuthService;
import com.example.loginapp.models.Board;
import com.example.loginapp.models.Category;
import com.example.loginapp.models.Card;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private AuthService authService;



    // Board Methods

    public void getBoards(int userId, String token, final Callback<List<Board>> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<List<Board>> call = authService.getBoards(userId, token);
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(@NonNull Call<List<Board>> call, @NonNull Response<List<Board>> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Board>> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void createBoard(int userId, Board board, String token, final Callback<Board> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Board> call = authService.createBoard(userId, board, token);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void getBoardDetail(int boardId, String token, final Callback<Board> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Board> call = authService.getBoard(boardId, token);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void updateBoard(int boardId, Board board, String token, final Callback<Board> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Board> call = authService.updateBoard(boardId, board, token);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void deleteBoard(int boardId, String token, final Callback<Void> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Void> call = authService.deleteBoard(boardId, token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    // Category Methods

    public void getCategories(int boardId, String token, final Callback<List<Category>> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<List<Category>> call = authService.getCategories(boardId, token);
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void createCategory(int boardId, Category category, String token, final Callback<Category> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Category> call = authService.createCategory(boardId, category, token);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void getCategory(int boardId, int categoryId, String token, final Callback<Category> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Category> call = authService.getCategory(boardId, categoryId, token);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void updateCategory(int boardId, int categoryId, Category category, String token, final Callback<Category> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Category> call = authService.updateCategory(boardId, categoryId, category, token);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void deleteCategory(int boardId, int categoryId, String token, final Callback<Void> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Void> call = authService.deleteCategory(boardId, categoryId, token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    // Card Methods

    public void getCards(int categoryId, String token, final Callback<List<Card>> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<List<Card>> call = authService.getCards(categoryId, token);
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(@NonNull Call<List<Card>> call, @NonNull Response<List<Card>> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Card>> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void createCard(int categoryId, Card card, String token, final Callback<Card> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Card> call = authService.createCard(categoryId, card, token);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(@NonNull Call<Card> call, @NonNull Response<Card> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Card> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void getCard(int categoryId, int cardId, String token, final Callback<Card> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Card> call = authService.getCard(categoryId, cardId, token);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(@NonNull Call<Card> call, @NonNull Response<Card> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Card> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void updateCard(int categoryId, int cardId, Card card, String token, final Callback<Card> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Card> call = authService.updateCard(categoryId, cardId, card, token);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(@NonNull Call<Card> call, @NonNull Response<Card> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Card> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }

    public void deleteCard(int categoryId, int cardId, String token, final Callback<Void> callback) {
        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);
        Call<Void> call = authService.deleteCard(categoryId, cardId, token);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call,response);
                } else {
                    callback.onFailure(call, new Throwable("Error: " + response.code()));

                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(call,t);
            }
        });
    }
}
