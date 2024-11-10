package com.example.loginapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.adapters.CategoryAdapter;
import com.example.loginapp.models.Board;
import com.example.loginapp.models.Category;

import java.util.ArrayList;
import java.util.List;

public class BoardDetailActivity extends AppCompatActivity {
    private TextView boardTitle;
    private RecyclerView recyclerViewCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_detail);

        boardTitle = findViewById(R.id.boardTitle);
        recyclerViewCategories = findViewById(R.id.recyclerViewCategories);

        if (recyclerViewCategories != null) {
            recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        }

        // Retrieve the board name from the Intent and set it to boardTitle
        String title = getIntent().getStringExtra("boardTitle");
        if (boardTitle != null && title != null) {
            boardTitle.setText(title);
        }

        // Initialize categories for this board
        categories = new ArrayList<>();
        categories.add(new Category("To Do"));
        categories.add(new Category("Doing"));
        categories.add(new Category("Done"));

        // Setup RecyclerView with CategoryAdapter
        categoryAdapter = new CategoryAdapter(categories);
        recyclerViewCategories.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategories.setAdapter(categoryAdapter);
    }
}
