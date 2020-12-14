package com.example.myapplication;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class tableData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "data")
    private String name;
    @ColumnInfo(name = "playerData")
    private String playerData;

    public void setPlayerData(String temp)
    {
        playerData = temp;
    }
    public String getPlayerData(){
        return playerData;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name= name;
    }
}
