package com.example.loginapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.models.Board;
import com.example.loginapp.R;
import com.example.loginapp.adapters.BoardAdapter;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity implements BoardAdapter.OnBoardClickListener {
    private RecyclerView recyclerViewBoards;
    private BoardAdapter boardAdapter;
    private List<Board> boards;
    private Button buttonCreateBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);


        recyclerViewBoards = findViewById(R.id.recyclerViewBoards);
        buttonCreateBoard = findViewById(R.id.buttonCreateBoard);




        boards = new ArrayList<>();
        boardAdapter = new BoardAdapter(boards,this);

        recyclerViewBoards.setAdapter(boardAdapter);
        recyclerViewBoards.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


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
    Board board = new Board()



    @Override
    public void onBoardClick(Board board){
        Intent intent= new Intent(this,BoardDetailActivity.class);
        intent.putExtra("boardTitle",board.getTitle());
        startActivity(intent);
    }


    private void showCreateBoardDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Create New Board");

        final EditText boardNameEditText = new EditText(this);
        builder.setView(boardNameEditText);

        builder.setPositiveButton("Create", (dialog, which) -> {
            String boardName = boardNameEditText.getText().toString().trim();
            if (!boardName.isEmpty()) {
                Board board = new Board(boardName, "Description of" + boardName);
                boards.add(board);
//                createCategoriesForBoard(newBoard);
                boardAdapter.notifyItemInserted(boards.size()-1);
//                boardAdapter.notifyDataSetChanged();

                recyclerViewBoards.setVisibility(RecyclerView.VISIBLE);
                buttonCreateBoard.setVisibility(RecyclerView.VISIBLE);
                Toast.makeText(BoardActivity.this, "Board Created", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(BoardActivity.this, "Please enter a board name", Toast.LENGTH_SHORT).show();


            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void createCategoriesForBoard(Board board) {
        board.addCategory("To-Do");
        board.addCategory("Doing");
        board.addCategory("Done");
    }
}
