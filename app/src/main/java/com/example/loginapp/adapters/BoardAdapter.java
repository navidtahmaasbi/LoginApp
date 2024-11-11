package com.example.loginapp.adapters;

// BoardAdapter.java
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.activities.BoardDetailActivity;
import com.example.loginapp.models.Board;
import com.example.loginapp.R;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {
    private List<Board> boards;
    private OnBoardClickListener listener;

    public BoardAdapter(List<Board> boards,OnBoardClickListener listener) {
        this.boards = boards;
        this.listener =listener;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board, parent, false);
        return new BoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, int position) {
        Board board = boards.get(position);
        holder.boardTitle.setText(board.getTitle());


        holder.itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(v.getContext(), BoardDetailActivity.class);
                    intent.putExtra("board_name",board.getTitle());
                    v.getContext().startActivity(intent);
                });
        // Bind data here
    }

    @Override
    public int getItemCount() {
        return boards.size();
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder {
        TextView boardTitle;
        TextView boardDescription;
        // Define the views here (e.g., TextView, ImageView)

        public BoardViewHolder(View itemView) {
            super(itemView);
            boardTitle = itemView.findViewById(R.id.boardTitle);
            boardDescription = itemView.findViewById(R.id.boardDescription);  // Assuming this TextView exists in item_board.xml

        }
    }

    public interface OnBoardClickListener{
        void onBoardClick(Board board);
    }
}

