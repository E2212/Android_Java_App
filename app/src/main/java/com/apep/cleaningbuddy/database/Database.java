package com.apep.cleaningbuddy.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.apep.cleaningbuddy.models.CompletedTask;
import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.models.User;

@androidx.room.Database(entities = {User.class, Room.class, Task.class, CompletedTask.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RoomDao roomDao();
    public abstract TaskDao taskDao();
    public abstract CompletedTaskDao completedTaskDao();

    private static volatile Database INSTANCE;

    public static Database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    Database.class, "CleaningBuddyDB")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Database getTestDatabase(final Context context) {
        return Room.inMemoryDatabaseBuilder(context.getApplicationContext(), Database.class)
                .allowMainThreadQueries()
                .build();
    }
}
