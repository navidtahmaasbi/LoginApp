package com.example.loginapp;

public class CategoryRequest {
    private String name;

    // Constructor
    public CategoryRequest(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
