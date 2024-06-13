package com.apep.cleaningbuddy.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room")
public class Room {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;

    public Room() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
