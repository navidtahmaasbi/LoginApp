package com.example.loginapp.adapters;

// BoardAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.models.Board;
import com.example.loginapp.R;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private List<Board> boards;

    public BoardAdapter(List<Board> boards) {
        this.boards = boards;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, int position) {
        Board board = boards.get(position);
        // Bind data here
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        // Define the views here (e.g., TextView, ImageView)

        public BoardViewHolder(View itemView) {
            super(itemView);
        }
    }
}

