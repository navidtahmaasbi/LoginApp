package com.example.loginapp;

public class BoardRequest {
    private String title;



    // Constructor
    public BoardRequest(String title ) {
        this.title = title;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
