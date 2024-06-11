package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.apep.cleaningbuddy.database.Database;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;

    @Ignore
    private List<Task> tasks = new ArrayList<>();

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

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
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

    public static Room getRoom(Context context, int roomId) {
        Room room = Database.getDatabase(context).roomDao().getRoom(roomId);
        List<Task> tasks = Database.getDatabase(context).taskDao().getRoomTasks(room.getId());
        if (tasks != null) {
            room.setTasks(tasks);
        }
        return room;
    }

    public static List<Room> getAll(Context context) {
        List<Room> rooms = Database.getDatabase(context).roomDao().getAll();
        for (Room room : rooms) {
            List<Task> tasks = Database.getDatabase(context).taskDao().getRoomTasks(room.getId());
            if (tasks != null) {
                for (Task task : tasks) {
                    if (task.getUser() == null && task.getUserId() != null) {
                        User user = Database.getDatabase(context).userDao().getUser(task.getUserId());
                        if (user != null) {
                            task.setUser(user);
                        }
                    }
                }
                room.setTasks(tasks);
            }
        }
        return rooms;
    }
}
