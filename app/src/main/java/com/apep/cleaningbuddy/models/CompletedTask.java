package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.apep.cleaningbuddy.database.Database;
import com.apep.cleaningbuddy.utils.DateConverter;

import java.util.Date;
import java.util.List;

@Entity(
    foreignKeys = {
        @ForeignKey(entity = Task.class, parentColumns = "id", childColumns = "taskId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.SET_NULL)
    },
    indices = {@Index(value = "userId"), @Index(value = "taskId")}
)
public class CompletedTask {
    @PrimaryKey(autoGenerate = true)
    private Integer id;
    @TypeConverters({DateConverter.class})
    private Date completionDate;
    private Integer taskId;
    private Integer userId;

    @Ignore
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public static void addCompletedTask(Context context, CompletedTask completedTask) {
        if (completedTask != null) {
            Database.getDatabase(context).completedTaskDao().insert(completedTask);
        }
    }

    public static List<CompletedTask> getAll(Context context) {
        return Database.getDatabase(context).completedTaskDao().getAll();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
