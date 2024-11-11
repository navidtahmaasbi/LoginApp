package com.example.loginapp.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private String title;
    private List<String> categories;



    // Constructor
    public Board(String title ){
        this.title =title;
        this.categories = new ArrayList<>();
    }
    public String getTitle(){
        return title;
    }

    public List<String> getCategories() {
        return categories;
    }
    public void addCategory(String category){
        categories.add(category);
    }

    public void  setTitle(String title){
        this.title= title;

    }
}
