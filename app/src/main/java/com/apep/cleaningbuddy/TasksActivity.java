package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TasksActivity extends BaseActivity {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        addNavbarListeners();

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
                // Navigate to NewTaskActivity
                Intent intent = new Intent(TasksActivity.this, NewTaskActivity.class);
                startActivity(intent);
            }
        });
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
