package com.apep.cleaningbuddy;


import static org.junit.Assert.assertEquals;

import com.apep.cleaningbuddy.models.Task;

import org.junit.Test;

public class TaskTest {

    @Test
    public void createTask_ShouldAddTask_WhenDataIsValid() {
        // Arrange
        String taskName = "Test Task";
        Task task = new Task();
        task.setName(taskName);

        // Act
        Task.addTask(null, task);

        // Assert
        assertEquals(taskName, Task.getTask(null, task.getId()).getName());
    }

    @Test
    public void updateTask_ShouldModifyTask_WhenDataIsValid() {
        // Arrange
        String originalTaskName = "Original Task";
        String updatedTaskName = "Updated Task";
        Task task = new Task();
        task.setName(originalTaskName);
        Task.addTask(null, task);

        // Act
        task.setName(updatedTaskName);
        Task.updateTask(null, task);

        // Assert
        assertEquals(updatedTaskName, Task.getTask(null, task.getId()).getName());
    }
}
