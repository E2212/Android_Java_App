package com.apep.cleaningbuddy.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.apep.cleaningbuddy.models.CompletedTask;

import java.util.List;

@Dao
public interface CompletedTaskDao {

    @Query("SELECT * FROM completed_task ")
    List<CompletedTask> getAll();

    @Insert
    void insert(CompletedTask completedTask);
}
