package com.example.loginapp.models;



import java.util.List;

public class Project {
    private int id; // Primary Key
    private int userId; // ID of the user who owns the project
    private String name; // Project name
    private String description; // Project description
    private String startDate; // Start date of the project
    private String endDate; // End date of the project
    private List<Task> tasks; // List of tasks associated with this project

    // Constructors
    public Project() {
    }

    public Project(int id, int userId, String name, String description, String startDate, String endDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
