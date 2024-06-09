package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TasksActivity extends AppCompatActivity {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        yourTasksButton = findViewById(R.id.Tasks_yourTasks_btn_id);
        openTasksButton = findViewById(R.id.Tasks_openTasks_btn_id);
        allTasksButton = findViewById(R.id.Tasks_allTasks_btn_id);
        addButton = findViewById(R.id.Tasks_add_btn_id);

        yourTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Your tasks" button click
                Toast.makeText(TasksActivity.this, "You are already in Your Tasks", Toast.LENGTH_SHORT).show();
            }
        });

        openTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to OpenTasksActivity
                Intent intent = new Intent(TasksActivity.this, OpenTasksActivity.class);
                startActivity(intent);
            }
        });

        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AllTasksActivity
                Intent intent = new Intent(TasksActivity.this, AllTasksActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Add" button click
                Toast.makeText(TasksActivity.this, "Add button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup click listeners for table rows
        setupTaskRowClickListener(R.id.activity_tasks_item_task1, "Curtain cleaning", "Weekly", "Room 6", "You", "Curtains must be washed at 60°C");
        setupTaskRowClickListener(R.id.activity_tasks_item_task2, "Bedding", "Weekly", "Room 5", "You", "Change bedding weekly");
        setupTaskRowClickListener(R.id.activity_tasks_item_task3, "Task 7", "Monthly", "Room 4", "You", "Some description here");
    }

    private void setupTaskRowClickListener(int rowId, final String taskName, final String interval, final String room, final String assignedUser, final String description) {
        TableRow taskRow = findViewById(rowId);
        taskRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TasksActivity.this, TaskDetailsActivity.class);
                intent.putExtra("TASK_NAME", taskName);
                intent.putExtra("INTERVAL", interval);
                intent.putExtra("ROOM", room);
                intent.putExtra("ASSIGNED_USER", assignedUser);
                intent.putExtra("DESCRIPTION", description);
                startActivity(intent);
            }
        });
    }
}
