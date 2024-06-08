package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.apep.cleaningbuddy.database.Database;

import java.util.List;

@Entity
public class Room {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;

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

    public static void addRoom(Context context, Room room) {
        if (room != null) {
            Database.getDatabase(context).roomDao().insert(room);
        }
    }

    public static void updateRoom(Context context, Room room) {
        if (room != null) {
            Database.getDatabase(context).roomDao().update(room);
        }
    }

    public static void deleteRoom(Context context, Room room) {
        if (room != null) {
            Database.getDatabase(context).roomDao().delete(room);
        }
    }

    public static List<Room> getAll(Context context) {
        return Database.getDatabase(context).roomDao().getAll();
    }
}
