package com.ccs.Models;

public class LoginResponse {

    private String token;
    private String email;
    private String name;
    private String role;
    private Long userId;
    private String message;

    // Default constructor
    public LoginResponse() {
    }

    // Constructor for successful login
    public LoginResponse(String token, String email, String name, String role, Long userId) {
        this.token = token;
        this.email = email;
        this.name = name;
        this.role = role;
        this.userId = userId;
        this.message = "Login successful";
    }

    // Constructor for error response
    public LoginResponse(String message) {
        this.message = message;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
