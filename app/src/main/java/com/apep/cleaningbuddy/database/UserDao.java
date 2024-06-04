package com.apep.cleaningbuddy.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.models.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user ")
    List<User> getAll();
    @Insert
    void insert(User user);
    @Update
    void update(User user);
}
