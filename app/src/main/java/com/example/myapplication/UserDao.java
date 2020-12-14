package com.example.myapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(tableData tableData);

    @Delete
    void delete(tableData tableData);

    @Query("SELECT * FROM tableData")
    List<tableData> getAll();
}
