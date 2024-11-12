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


        repository = new Repository();



        recyclerViewBoards = findViewById(R.id.recyclerViewBoards);
        buttonCreateBoard = findViewById(R.id.buttonCreateBoard);


        boards = new ArrayList<>();
        boardAdapter = new BoardAdapter(boards, this);


        recyclerViewBoards.setAdapter(boardAdapter);
        recyclerViewBoards.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


            recyclerViewBoards.setVisibility(RecyclerView.VISIBLE);
            buttonCreateBoard.setVisibility(RecyclerView.VISIBLE);



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
    protected void onStart(){
        super.onStart();
        int userId = getuserId();
        String token= getToken();
        getBoards(userId,token);
    }



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


                createCategoriesForBoard(board);
                boardAdapter.notifyDataSetChanged();
                createBoard(userId, board, token);

                Intent intent = new Intent(BoardActivity.this, BoardDetailActivity.class);
                intent.putExtra("boardTitle", boardName);  // Pass the board name to the detail activity
                startActivity(intent);




            } else {
                Toast.makeText(BoardActivity.this, "Please enter a board name", Toast.LENGTH_SHORT).show();


            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
    private void createBoard(int userId, Board board, String token) {
        repository.createBoard(userId, board, token, new Callback<Board>() {
            @Override
            public void onResponse(@NonNull Call<Board> call, @NonNull Response<Board> response) {
                if (response.isSuccessful()) {
                    Board createdBoard = response.body();
                    if (createdBoard != null) {
                        // Update UI with the newly created board
                        boards.add(createdBoard);
                        boardAdapter.notifyItemInserted(boards.size() - 1);  // Update RecyclerView
                        Toast.makeText(BoardActivity.this, "Board Created Successfully", Toast.LENGTH_SHORT).show();
                        // Add to your RecyclerView or list of boards as needed
                    }
                } else {
                    Toast.makeText(BoardActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Board> call, @NonNull Throwable t) {
                Toast.makeText(BoardActivity.this, "Error creating board: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Update visibility as needed
        recyclerViewBoards.setVisibility(View.VISIBLE);
        buttonCreateBoard.setVisibility(View.VISIBLE);
    }




    private void getBoards(int userId, String token) {
        repository.getBoards(userId, token, new Callback<List<Board>>() {
            @Override
            public void onResponse(@NonNull Call<List<Board>> call, @NonNull Response<List<Board>> response) {
                if (response.isSuccessful()) {
                    List<Board> fetchedBoards = response.body();
                    if (fetchedBoards != null && !fetchedBoards.isEmpty()) {
                        // Clear current list and add the fetched boards
                        boards.clear();
                        boards.addAll(fetchedBoards);

                        // Notify adapter to refresh the RecyclerView
                        boardAdapter.notifyItemInserted(boards.size() - 1);  // Update RecyclerView
//                        Toast.makeText(BoardActivity.this, "Boards fetched successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(BoardActivity.this, "No boards found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(BoardActivity.this, "Error fetching boards: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Board>> call, @NonNull Throwable t) {
                Toast.makeText(BoardActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
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
