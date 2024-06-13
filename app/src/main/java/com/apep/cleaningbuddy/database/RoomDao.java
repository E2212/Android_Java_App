package com.apep.cleaningbuddy.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.apep.cleaningbuddy.models.Room;

import java.util.List;

@Dao
public interface RoomDao {
    @Query("SELECT * FROM room")
    List<Room> getAll();
    @Insert
    void insert(Room room);
    @Update
    void update(Room room);
    @Delete
    void delete(Room room);
    @Query("SELECT * FROM room WHERE id = :id")
    Room getRoom(int id);
}
