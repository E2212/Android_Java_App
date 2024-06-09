package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpenTasksActivity extends AppCompatActivity {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_tasks);

        yourTasksButton = findViewById(R.id.activity_open_tasks_item_yourTasksButton);
        openTasksButton = findViewById(R.id.activity_open_tasks_item_openTasksButton);
        allTasksButton = findViewById(R.id.activity_open_tasks_item_allTasksButton);
        addButton = findViewById(R.id.activity_open_tasks_item_addButton);

        yourTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to TasksActivity
                Intent intent = new Intent(OpenTasksActivity.this, TasksActivity.class);
                startActivity(intent);
            }
        });

        openTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Open tasks" button click
                Toast.makeText(OpenTasksActivity.this, "You are already in Open Tasks", Toast.LENGTH_SHORT).show();
            }
        });

        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AllTasksActivity
                Intent intent = new Intent(OpenTasksActivity.this, AllTasksActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "Add" button click
                Toast.makeText(OpenTasksActivity.this, "Add button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup click listeners for table rows
        setupTaskRowClickListener(R.id.activity_open_tasks_item_task1, "Curtain cleaning", "Weekly", "Room 6", "You", "Curtains must be washed at 60°C");
        setupTaskRowClickListener(R.id.activity_open_tasks_item_task2, "Toilet cleaning", "Daily", "Room 5", "Ensar", "Clean the toilets daily");
        setupTaskRowClickListener(R.id.activity_open_tasks_item_task3, "Carpet cleaning", "Monthly", "Room 4", "Paco", "Vacuum and clean carpets monthly");
        setupTaskRowClickListener(R.id.activity_open_tasks_item_task4, "Bedding", "Weekly", "Room 3", "You", "Change bedding weekly");
        setupTaskRowClickListener(R.id.activity_open_tasks_item_task5, "Electronics", "Weekly", "Room 2", "Khalid", "Dust and clean electronics weekly");
    }

    private void setupTaskRowClickListener(int rowId, final String taskName, final String interval, final String room, final String assignedUser, final String description) {
        TableRow taskRow = findViewById(rowId);
        taskRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OpenTasksActivity.this, TaskDetailsActivity.class);
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
