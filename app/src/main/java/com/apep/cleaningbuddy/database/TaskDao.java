package com.apep.cleaningbuddy.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.apep.cleaningbuddy.models.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task ")
    List<Task> getAll();
    @Insert
    void insert(Task task);
    @Update
    void update(Task task);
    @Delete
    void delete(Task task);
}
