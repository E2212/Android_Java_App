package com.apep.cleaningbuddy.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.apep.cleaningbuddy.models.CompletedTask;

import java.util.List;

@Dao
public interface CompletedTaskDao {

    @Query("SELECT * FROM CompletedTask ")
    List<CompletedTask> getAll();

    @Insert
    void insert(CompletedTask completedTask);

    @Query("SELECT * FROM CompletedTask WHERE taskId = :taskId ORDER BY completionDate DESC ")
    List<CompletedTask> getCompletedTasks(int taskId);

    @Query("SELECT * FROM CompletedTask WHERE taskId = :taskId ORDER BY completionDate DESC LIMIT 1")
    CompletedTask getLatestCompletedTasks(int taskId);

    @Query("DELETE FROM CompletedTask WHERE taskId = :taskId")

    void deleteTask(int taskId);

    @Query("SELECT * FROM CompletedTask WHERE taskId = :taskId ORDER BY completionDate ASC ")
    List<CompletedTask> getCompletedTasksAsc(int taskId);
}
