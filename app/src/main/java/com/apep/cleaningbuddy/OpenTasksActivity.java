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

        yourTasksButton = findViewById(R.id.openTasks_yourTasks_btn_id);
        openTasksButton = findViewById(R.id.openTasks_openTasks_btn_id);
        allTasksButton = findViewById(R.id.openTasks_allTasks_btn_id);
        addButton = findViewById(R.id.openTasks_add_btn_id);

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
                // Navigate to NewTaskActivity
                Intent intent = new Intent(OpenTasksActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });

        Button roomsButton = findViewById(R.id.btn_rooms);
        roomsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomsActivity.class);
            startActivity(intent);
            finish();
        });

        Button tasksButton = findViewById(R.id.btn_tasks);
        tasksButton.setOnClickListener(v -> {
            // Current Activity, no action needed
        });

        Button profileButton = findViewById(R.id.btn_profile);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
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
