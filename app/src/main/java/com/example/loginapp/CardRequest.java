package com.example.loginapp;

public class CardRequest {
    private String title;
    private String description;
    private String categoryId;

    // Constructor
    public CardRequest(String title, String description, String categoryId) {
        this.title = title;
        this.description = description;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
