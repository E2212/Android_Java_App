package com.apep.cleaningbuddy;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import com.apep.cleaningbuddy.models.User;

import org.junit.Test;

public class RegisterTest {

    @Test
    public void register_ShouldAddUser_WhenDataIsValid() {
        // Arrange
        String username = "newUser";
        String password = "newPassword";
        User user = new User();
        user.setUsername(username);
        user.setHashedPassword(password.getBytes());

        // Act
        User.addUser(null, user);

        // Assert
        assertNotNull(User.getLoggedInUser());
        assertEquals(username, User.getLoggedInUser().getUsername());
    }

    @Test
    public void register_ShouldFail_WhenUsernameIsTaken() {
        // Arrange
        String username = "existingUser";
        String password = "newPassword";
        User user1 = new User();
        user1.setUsername(username);
        user1.setHashedPassword(password.getBytes());

        User user2 = new User();
        user2.setUsername(username);
        user2.setHashedPassword(password.getBytes());

        // Act
        User.addUser(null, user1);
        User.addUser(null, user2);

        // Assert
        assertNotEquals(User.getLoggedInUser().getUsername(), user2.getUsername());
    }
}

