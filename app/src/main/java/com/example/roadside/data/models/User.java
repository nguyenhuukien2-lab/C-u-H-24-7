package com.example.roadside.data.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    @NonNull
    private String id = "";
    private String phoneNumber;
    private String fullName;
    private String avatarUrl;
    private String email;
    private String password;
    private String token;

    public User() { }

    public User(String id, String phoneNumber, String fullName) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
    }

    public User(int id, String fullName, String email, String phoneNumber, String password, String token) {
        this.id = String.valueOf(id);
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.token = token;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public void setId(int id) { this.id = String.valueOf(id); }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getName() { return fullName != null ? fullName : email; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
}
