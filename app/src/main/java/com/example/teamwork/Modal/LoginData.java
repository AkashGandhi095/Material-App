package com.example.teamwork.Modal;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class LoginData {

    @PrimaryKey(autoGenerate = true)
    public int uId;

    private String email;

    private String name;

    private String password;

    public LoginData(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public int getuId() {
        return uId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
