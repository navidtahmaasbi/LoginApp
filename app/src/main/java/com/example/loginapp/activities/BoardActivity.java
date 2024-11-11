package com.example.loginapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.BoardRequest;
import com.example.loginapp.Web.ApiClient;
import com.example.loginapp.Web.AuthService;
import com.example.loginapp.models.Board;
import com.example.loginapp.R;
import com.example.loginapp.adapters.BoardAdapter;
import com.example.loginapp.repository.Repository;
import com.example.loginapp.repository.RepositoryCallback;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BoardActivity extends AppCompatActivity implements BoardAdapter.OnBoardClickListener {
    private RecyclerView recyclerViewBoards;
    private BoardAdapter boardAdapter;
    private List<Board> boards;
    private Button buttonCreateBoard;
    private Repository repository;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);



        recyclerViewBoards = findViewById(R.id.recyclerViewBoards);
        buttonCreateBoard = findViewById(R.id.buttonCreateBoard);


        boards = new ArrayList<>();
        boardAdapter = new BoardAdapter(boards, this);


        recyclerViewBoards.setAdapter(boardAdapter);
        recyclerViewBoards.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        if (boards.isEmpty()) {
            recyclerViewBoards.setVisibility(RecyclerView.GONE);
            buttonCreateBoard.setVisibility(RecyclerView.VISIBLE);
        } else {
            recyclerViewBoards.setVisibility(RecyclerView.VISIBLE);
            buttonCreateBoard.setVisibility(RecyclerView.VISIBLE);
        }

        buttonCreateBoard.setOnClickListener(v -> {
            showCreateBoardDialog();

        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                // Handle drag-and-drop here (you can move boards to different positions in the list)

                int fromPosition = viewHolder.getAbsoluteAdapterPosition();
                int toPosition = target.getAbsoluteAdapterPosition();
                Board moveBoard = boards.get(fromPosition);
                boards.remove(fromPosition);
                boards.add(toPosition, moveBoard);
                boardAdapter.notifyItemMoved(fromPosition, toPosition);
                return true;


            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // Handle swipe (e.g., delete the board)
                int position = viewHolder.getAbsoluteAdapterPosition();
                boards.remove(position);
                boardAdapter.notifyItemRemoved(position);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewBoards);

    }
//    private void createBoardRequest(int userId, Board board, String token){
//
////        repository = new Repository(authService);
//        repository.createBoard(userId, board, token, new RepositoryCallback<Board>() {
//            @Override
//            public void onSuccess(Board board) {
//                boards.add(board);
//                boardAdapter.notifyItemInserted(boards.size()-1);
//                Toast.makeText(BoardActivity.this, "Board Created",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(String message) {
//                Toast.makeText(BoardActivity.this, "Error creating board",Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        recyclerViewBoards.setVisibility(RecyclerView.VISIBLE);
//        buttonCreateBoard.setVisibility(RecyclerView.VISIBLE);
//    }


    private void showCreateBoardDialog() {

//        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs",MODE_PRIVATE);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Board");

        final EditText boardNameEditText = new EditText(this);
        builder.setView(boardNameEditText);


        builder.setPositiveButton("Create", (dialog, which) -> {
            String boardName = boardNameEditText.getText().toString().trim();
            if (!boardName.isEmpty()) {
                Board board = new Board(boardName);
                int userId = getuserId();
                String token = getToken();


//                boards.add(board);
                createCategoriesForBoard(board);
                boardAdapter.notifyDataSetChanged();
//                boardAdapter.notifyItemInserted(boards.size()-1);
                createBoard(userId, board, token);

                recyclerViewBoards.setVisibility(View.VISIBLE);
                buttonCreateBoard.setVisibility(View.VISIBLE);


//                Toast.makeText(BoardActivity.this, "Board Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BoardActivity.this, "Please enter a board name", Toast.LENGTH_SHORT).show();


            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void createBoard(int userId, Board board, String token) {
        // Define the Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8000/api/") // Change this to your actual base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Define the API service interface
        AuthService authService = retrofit.create(AuthService.class);

        // Make the API call to create the board
        Call<Board> call = authService.createBoard(userId, board, "Bearer " + token);
        call.enqueue(new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    // If successful, update UI with the newly created board
                    Board board = response.body();
                    if (board != null) {
                        boards.add(board); // Add the created board to the list
                        boardAdapter.notifyItemInserted(boards.size() - 1); // Notify adapter to update RecyclerView
                        Toast.makeText(BoardActivity.this, "Board Created Successfully", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // If the response is not successful, show an error
                    Toast.makeText(BoardActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                // Handle failure, such as network issues or timeout
                Toast.makeText(BoardActivity.this, "Error creating board: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Optionally, you can hide or show buttons, RecyclerView visibility here
        recyclerViewBoards.setVisibility(View.VISIBLE);
        buttonCreateBoard.setVisibility(View.VISIBLE);
    }

    public void getBoards(int userId, String token) {

        AuthService authService = ApiClient.getRetrofitInstance(null).create(AuthService.class);

        // Make the API call to get boards
        Call<List<Board>> call = authService.getBoards(userId, "Bearer " + token);
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(@NonNull Call<List<Board>> call, @NonNull Response<List<Board>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Successfully retrieved the list of boards
                    List<Board> retrievedBoards = response.body();
                    boards.clear();               // Clear existing boards if any
                    boards.addAll(retrievedBoards); // Add retrieved boards to the list
                    boardAdapter.notifyDataSetChanged(); // Notify adapter to refresh the RecyclerView
                    Toast.makeText(BoardActivity.this, "Boards loaded", Toast.LENGTH_SHORT).show();
                } else {
                    // If the response is not successful, show an error message
                    Toast.makeText(BoardActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Board>> call, @NonNull Throwable t) {
                // Handle failure, such as network issues
                Toast.makeText(BoardActivity.this, "Error loading boards: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void onBoardClick(Board board) {
        Intent intent = new Intent(this, BoardDetailActivity.class);
        intent.putExtra("boardTitle", board.getTitle());
        startActivity(intent);
    }


    private void createCategoriesForBoard(Board board) {
        board.addCategory("To-Do");
        board.addCategory("Doing");
        board.addCategory("Done");
    }

    private int getuserId() {
        SharedPreferences preferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        return preferences.getInt("userId", -1);
    }

    private String getToken() {
        SharedPreferences preferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        return preferences.getString("token", null);
    }


}
