package com.example.loginapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.loginapp.R;
import com.example.loginapp.activities.BoardDetailActivity;
import com.example.loginapp.adapters.BoardAdapter;
import com.example.loginapp.models.Board;

import java.util.ArrayList;
import java.util.List;

public class BoardFragment extends Fragment implements BoardAdapter.OnBoardClickListener {
    private RecyclerView recyclerViewBoards;
    private BoardAdapter boardAdapter;
    private List<Board> boards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        //Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_board,container,false);

        recyclerViewBoards= view.findViewById(R.id.recyclerViewBoards);
        recyclerViewBoards.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
         boards = new ArrayList<>();


         boardAdapter = new BoardAdapter(boards,this);
         recyclerViewBoards.setAdapter(boardAdapter);
         return view;
    }

    @Override
    public void onBoardClick(Board board){
        Intent intent = new Intent(getContext(), BoardDetailActivity.class);
        intent.putExtra("board_name",board.getTitle());
        startActivity(intent);
    }
}
