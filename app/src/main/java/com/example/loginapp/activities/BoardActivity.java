package com.example.loginapp.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.models.Board;
import com.example.loginapp.R;
import com.example.loginapp.adapters.BoardAdapter;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends AppCompatActivity {
private RecyclerView recyclerViewBoards;
private BoardAdapter boardAdapter;
private List<Board> boards;

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_board);

    recyclerViewBoards = findViewById(R.id.recyclerViewBoards);
    recyclerViewBoards.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    boards = new ArrayList<>();

    boardAdapter = new BoardAdapter(boards);
    recyclerViewBoards.setAdapter(boardAdapter);

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target){
            // Handle drag-and-drop here (you can move boards to different positions in the list)

            int fromPosition = viewHolder.getAbsoluteAdapterPosition();
            int toPosition = target.getAbsoluteAdapterPosition();
            Board moveBoard = boards.get(fromPosition);
            boards.remove(fromPosition);
            boards.add(toPosition,moveBoard);
            boardAdapter.notifyItemMoved(fromPosition,toPosition);
            return true;



        }
        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction){
            // Handle swipe (e.g., delete the board)
            int position = viewHolder.getAbsoluteAdapterPosition();
            boards.remove(position);
            boardAdapter.notifyItemRemoved(position);
        }
    };
    ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
    itemTouchHelper.attachToRecyclerView(recyclerViewBoards);
}
}
