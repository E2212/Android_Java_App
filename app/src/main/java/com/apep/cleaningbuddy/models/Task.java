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
}