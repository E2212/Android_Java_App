package com.apep.cleaningbuddy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.apep.cleaningbuddy.models.User;

public class LoginTest {

    @Test
    public void login_ShouldReturnTrue_WhenCredentialsAreValid() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setUsername(username);
        user.setHashedPassword(password.getBytes());

        // Act
        boolean result = user.checkUserPassword(password.getBytes());

        // Assert
        assertTrue(result);
    }

    @Test
    public void login_ShouldReturnFalse_WhenPasswordIsInvalid() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        String wrongPassword = "wrongPassword";
        User user = new User();
        user.setUsername(username);
        user.setHashedPassword(password.getBytes());

        // Act
        boolean result = user.checkUserPassword(wrongPassword.getBytes());

        // Assert
        assertFalse(result);
    }
}
