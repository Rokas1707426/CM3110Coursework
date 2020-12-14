package com.example.myapplication;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {tableData.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}