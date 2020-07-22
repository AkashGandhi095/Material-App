package com.example.teamwork.Utils;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.teamwork.Modal.LoginData;

@Database(entities = LoginData.class , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DB_NAME = "LoggedUsers.db";
    private static AppDatabase appDatabase = null;

    public abstract LoginDao dao();

    public static AppDatabase getAppDatabase(Context context)
    {
        if(appDatabase == null)
        {
            synchronized (AppDatabase.class)
            {
                if(appDatabase == null)
                {
                    appDatabase = Room.databaseBuilder(context.getApplicationContext() ,
                            AppDatabase.class , DB_NAME).
                            build();
                }
            }
        }
        return appDatabase;
    }

}
