package com.apep.cleaningbuddy.models;

import android.content.Context;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.apep.cleaningbuddy.database.Database;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public static Task getTask(Context context, int taskId) {
        Task task = Database.getDatabase(context).taskDao().getTask(taskId);
        if (task.getRoomId() != null) {
            task.setRoom(Database.getDatabase(context).roomDao().getRoom(task.getRoomId()));
        }

        if (task.getUserId() != null) {
            task.setUser(Database.getDatabase(context).userDao().getUser(task.getUserId()));
        }

        List<CompletedTask> completedTasks = Database.getDatabase(context).completedTaskDao().getCompletedTasks(taskId);
        for (CompletedTask completedTask : completedTasks) {
            User user = Database.getDatabase(context).userDao().getUser(completedTask.getUserId());
            if (user != null) {
                completedTask.setUser(user);
            }
        }
        task.setTaskHistory(completedTasks);

        return task;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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
            Database.getDatabase(context).completedTaskDao().deleteTask(task.getId());
            Database.getDatabase(context).taskDao().delete(task);
        }
    }

    public static List<Task> getAll(Context context) {
        List<Task> tasks = Database.getDatabase(context).taskDao().getAll();
        for (Task task : tasks) {
            if (task.getRoomId() != null) {
                Room room = Database.getDatabase(context).roomDao().getRoom(task.getRoomId());
                if (room != null) {
                    task.setRoom(room);
                }
            }

            if (task.getUserId() != null) {
                User user = Database.getDatabase(context).userDao().getUser(task.getUserId());
                if (user != null) {
                    task.setUser(user);
                }
            }
        }
        return tasks;
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

    private void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<Task> getOpenTasks(Context context) {
        List<Task> openTasks = new ArrayList<>();
        for (Task task : Database.getDatabase(context).taskDao().getAllOpenTasks()) {
            CompletedTask completedTask = Database.getDatabase(context).completedTaskDao().getLatestCompletedTasks(task.getId());
            if (completedTask != null) {
                // Wanneer het verschil in dagen met het laatst voltooide moment lager
                // of gelijk is aan de interval verwijderen we deze uit de lijst
                LocalDate localDate = LocalDate.now();
                LocalDateTime localDateTime = localDate.atStartOfDay();
                Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                long millisDifference = now.getTime() - completedTask.getCompletionDate().getTime();
                long dayDifference = millisDifference / (1000 * 60 * 60 * 24);

                if (dayDifference <= task.interval) {
                    continue;
                }
            }

            openTasks.add(task);
        }
        return openTasks;
    }

    public static void completeTasks(Context context, List<Task> tasks) {
        for (Task task : tasks) {
            CompletedTask completedTask = new CompletedTask();
            completedTask.setTaskId(task.getId());
            completedTask.setUserId(User.getLoggedInUser().getId());

            LocalDate localDate = LocalDate.now();
            LocalDateTime localDateTime = localDate.atStartOfDay();
            Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
            completedTask.setCompletionDate(date);
            CompletedTask.addCompletedTask(context, completedTask);
        }
    }

    public static List<Task> getOpenUserTasks(Context context) {
        List<Task> openTasks = new ArrayList<>();
        for (Task task : Database.getDatabase(context).taskDao().getUserTasks(User.getLoggedInUser().getId())) {
            CompletedTask completedTask = Database.getDatabase(context).completedTaskDao().getLatestCompletedTasks(task.getId());
            if (completedTask != null) {
                // Wanneer het verschil in dagen met het laatst voltooide moment lager
                // of gelijk is aan de interval verwijderen we deze uit de lijst
                LocalDate localDate = LocalDate.now();
                LocalDateTime localDateTime = localDate.atStartOfDay();
                Date now = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
                long millisDifference = now.getTime() - completedTask.getCompletionDate().getTime();
                long dayDifference = millisDifference / (1000 * 60 * 60 * 24);

                if (dayDifference <= task.interval) {
                    continue;
                }
            }

            openTasks.add(task);
        }

        return openTasks;
    }

    public static List<CompletedTask> getDescTaskHistory(Context context, int taskId) {
        List<CompletedTask> completedTasks = Database.getDatabase(context).completedTaskDao().getCompletedTasks(taskId);
        for (CompletedTask completedTask : completedTasks) {
            if (completedTask.getUserId() != null) {
                completedTask.setUser(Database.getDatabase(context).userDao().getUser(completedTask.getUserId()));
            }
        }
        return completedTasks;
    }

    public static List<CompletedTask> getAscTaskHistory(Context context, int taskId) {
        List<CompletedTask> completedTasks = Database.getDatabase(context).completedTaskDao().getCompletedTasksAsc(taskId);
        for (CompletedTask completedTask : completedTasks) {
            if (completedTask.getUserId() != null) {
                completedTask.setUser(Database.getDatabase(context).userDao().getUser(completedTask.getUserId()));
            }
        }
        return completedTasks;
    }
}