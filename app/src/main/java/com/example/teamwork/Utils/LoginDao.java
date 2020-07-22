package com.example.teamwork.Utils;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.teamwork.Modal.LoginData;

@Dao
public interface LoginDao {

    @Insert
    void SaveLoginData(LoginData loginData);

    @Query("SELECT * FROM LoginData WHERE email = :mEmail AND password = :mPassword LIMIT 1 ")
    boolean getLoginData(String mEmail , String mPassword);


}
