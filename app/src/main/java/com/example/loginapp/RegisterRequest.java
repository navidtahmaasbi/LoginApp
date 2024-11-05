package com.example.loginapp;

public class RegisterRequest {
    private String name;
    private String email;
    private String dob;
    private String gender;
    private String mobile;
    private String password;

    public RegisterRequest(String name,String email,String dob,String gender,String mobile,String password){
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.gender = gender;
        this.mobile = mobile;
        this.password = password;
    }
}
