package com.ccs.Models;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username; // Maps to 'name' column in the database
    private String password;
    private String email;
    private String phone;

    public User(String username, String password, String email, String phone) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }

    public User() {
    }

    // Derive Role enum from discriminator value
    public Role getRole() {
        String discriminatorValue = getClass().getAnnotation(DiscriminatorValue.class) != null
                ? getClass().getAnnotation(DiscriminatorValue.class).value()
                : null;
        return discriminatorValue != null ? Role.valueOf(discriminatorValue) : null;
    }

    // Optionally, set discriminator value (handled by Hibernate, so setter is optional)
    public void setRole(Role role) {
        // No direct setter needed since Hibernate manages the discriminator
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}