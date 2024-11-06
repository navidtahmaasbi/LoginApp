package com.example.loginapp.models;

public class Card {
    private String title;
    private String description;
    // other fields...

    // Constructor
    public Card(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title (if needed)
    public void setTitle(String title) {
        this.title = title;
    }
    // Getter for title
    public String getDescription() {
        return description;
    }

    // Setter for title (if needed)
    public void setDescription(String description) {
        this.description = description;
    }
}
