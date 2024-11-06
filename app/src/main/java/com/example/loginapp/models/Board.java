package com.example.loginapp.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private String title;
    private String description;
    private List<String> categories;



    // Constructor
    public Board(String title, String decription){
        this.title =title;
        this.description=decription;
        this.categories = new ArrayList<>();
    }
    public String getTitle(){
        return title;
    }
    public String getDecription(){
        return description;
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
    public void setDecription(String description){
        this.description=description;
    }
}
