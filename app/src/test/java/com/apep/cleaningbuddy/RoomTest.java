package com.apep.cleaningbuddy;


import static org.junit.Assert.assertEquals;

import com.apep.cleaningbuddy.models.Room;

import org.junit.Test;

public class RoomTest {

    @Test
    public void createRoom_ShouldAddRoom_WhenDataIsValid() {
        // Arrange
        String roomName = "Test Room";
        Room room = new Room();
        room.setName(roomName);

        // Act
        Room.addRoom(null, room);

        // Assert
        assertEquals(roomName, Room.getRoom(null, room.getId()).getName());
    }

    @Test
    public void updateRoom_ShouldModifyRoom_WhenDataIsValid() {
        // Arrange
        String originalRoomName = "Original Room";
        String updatedRoomName = "Updated Room";
        Room room = new Room();
        room.setName(originalRoomName);
        Room.addRoom(null, room);

        // Act
        room.setName(updatedRoomName);
        Room.updateRoom(null, room);

        // Assert
        assertEquals(updatedRoomName, Room.getRoom(null, room.getId()).getName());
    }
}

