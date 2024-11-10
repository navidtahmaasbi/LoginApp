package com.example.loginapp.repository;

import androidx.annotation.NonNull;

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

    public Repository(AuthService authService) {
        this.authService = authService;
    }

    // Board Methods

    public void getBoards(int userId, String authToken, final RepositoryCallback<List<Board>> callback) {
        Call<List<Board>> call = authService.getBoards(userId, authToken);
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(@NonNull Call<List<Board>> call, @NonNull Response<List<Board>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Board>> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void createBoard(int userId, Board board, String authToken, final RepositoryCallback<Board> callback) {
        Call<Board> call = authService.createBoard(userId, board, authToken);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getBoard(int boardId, String authToken, final RepositoryCallback<Board> callback) {
        Call<Board> call = authService.getBoard(boardId, authToken);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void updateBoard(int boardId, Board board, String authToken, final RepositoryCallback<Board> callback) {
        Call<Board> call = authService.updateBoard(boardId, board, authToken);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void deleteBoard(int boardId, String authToken, final RepositoryCallback<Void> callback) {
        Call<Void> call = authService.deleteBoard(boardId, authToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    // Category Methods

    public void getCategories(int boardId, String authToken, final RepositoryCallback<List<Category>> callback) {
        Call<List<Category>> call = authService.getCategories(boardId, authToken);
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void createCategory(int boardId, Category category, String authToken, final RepositoryCallback<Category> callback) {
        Call<Category> call = authService.createCategory(boardId, category, authToken);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getCategory(int boardId, int categoryId, String authToken, final RepositoryCallback<Category> callback) {
        Call<Category> call = authService.getCategory(boardId, categoryId, authToken);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void updateCategory(int boardId, int categoryId, Category category, String authToken, final RepositoryCallback<Category> callback) {
        Call<Category> call = authService.updateCategory(boardId, categoryId, category, authToken);
        call.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(@NonNull Call<Category> call, @NonNull Response<Category> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Category> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void deleteCategory(int boardId, int categoryId, String authToken, final RepositoryCallback<Void> callback) {
        Call<Void> call = authService.deleteCategory(boardId, categoryId, authToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    // Card Methods

    public void getCards(int categoryId, String authToken, final RepositoryCallback<List<Card>> callback) {
        Call<List<Card>> call = authService.getCards(categoryId, authToken);
        call.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(@NonNull Call<List<Card>> call, @NonNull Response<List<Card>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Card>> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void createCard(int categoryId, Card card, String authToken, final RepositoryCallback<Card> callback) {
        Call<Card> call = authService.createCard(categoryId, card, authToken);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(@NonNull Call<Card> call, @NonNull Response<Card> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Card> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getCard(int categoryId, int cardId, String authToken, final RepositoryCallback<Card> callback) {
        Call<Card> call = authService.getCard(categoryId, cardId, authToken);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(@NonNull Call<Card> call, @NonNull Response<Card> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Card> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void updateCard(int categoryId, int cardId, Card card, String authToken, final RepositoryCallback<Card> callback) {
        Call<Card> call = authService.updateCard(categoryId, cardId, card, authToken);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(@NonNull Call<Card> call, @NonNull Response<Card> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Card> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void deleteCard(int categoryId, int cardId, String authToken, final RepositoryCallback<Void> callback) {
        Call<Void> call = authService.deleteCard(categoryId, cardId, authToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }
}
