package com.apep.cleaningbuddy.database;

import android.content.Context;
import androidx.room.RoomDatabase;

import com.apep.cleaningbuddy.models.CompletedTask;
import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.models.User;

@androidx.room.Database(entities = {User.class, Room.class, Task.class, CompletedTask.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RoomDao roomDao();
    public abstract TaskDao taskDao();
    public abstract CompletedTaskDao completedTaskDao();

    public static Database getDatabase(Context context) {
        Database database;
        synchronized (Database.class) {
            database = androidx.room.Room.databaseBuilder(context, Database.class, "CleaningBuddyDB").allowMainThreadQueries().build();
        }
        return database;
    }
}