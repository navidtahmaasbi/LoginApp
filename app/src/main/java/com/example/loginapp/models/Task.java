package com.example.loginapp.models;

public class Task {
    private int id; // Primary Key
    private int projectId; // ID of the project this task belongs to
    private String title; // Task title
    private String description; // Task description
    private String status; // Task status (e.g., to_do, in_progress, completed)
    private String dueDate; // Due date of the task

    // Constructors
    public Task() {
    }

    public Task(int id, int projectId, String title, String description, String status, String dueDate) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
