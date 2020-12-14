package com.example.myapplication;

import android.content.Context;

import androidx.room.Room;

public class databaseClient {
    private Context context;
    private static databaseClient mInstance;

    //our app database object
    private AppDatabase appDatabase;

    private databaseClient(Context context) {
        this.context = context;

        //creating the app database with Room database builder
        //MyDB is the name of the database
        appDatabase = Room.databaseBuilder(context, AppDatabase.class, "MyDB").build();
    }

    public static synchronized databaseClient getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new databaseClient(context);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
