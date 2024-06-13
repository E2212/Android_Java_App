package com.apep.cleaningbuddy.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(
        foreignKeys = {
                @ForeignKey(entity = Room.class, parentColumns = "id", childColumns = "roomId", onDelete = ForeignKey.SET_NULL),
                @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.SET_NULL)
        },
        indices = {@Index(value = "userId"), @Index(value = "roomId")}
)
public class Task {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String name;
    private boolean completed;
    private Integer interval;
    private String description;
    private Integer userId;
    private Integer roomId;

    @Ignore
    private User user;

    @Ignore
    private List<CompletedTask> taskHistory = new ArrayList<>();

    @Ignore
    private Room room;

    // Default constructor
    public Task() {
    }

    // Getters and setters
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CompletedTask> getTaskHistory() {
        return taskHistory;
    }

    public void setTaskHistory(List<CompletedTask> taskHistory) {
        this.taskHistory = taskHistory;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return name;
    }
}
