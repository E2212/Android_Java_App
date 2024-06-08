package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.apep.cleaningbuddy.database.Database;

import java.util.List;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Room.class, parentColumns = "id", childColumns = "roomId", onDelete = ForeignKey.SET_NULL),
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.SET_NULL)
        }
)
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private Integer interval;
    private String description;
    private Integer userId;
    private Integer roomId;


    public static void addTask(Context context, Task task) {
        if (task != null) {
            Database.getDatabase(context).taskDao().insert(task);
        }
    }

    public static void updateTask(Context context, Task task) {
        if (task != null) {
            Database.getDatabase(context).taskDao().update(task);
        }
    }

    public static void deleteTask(Context context, Task task) {
        if (task != null) {
            Database.getDatabase(context).taskDao().delete(task);
        }
    }

    public static List<Task> getAll(Context context) {
        return Database.getDatabase(context).taskDao().getAll();
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

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
