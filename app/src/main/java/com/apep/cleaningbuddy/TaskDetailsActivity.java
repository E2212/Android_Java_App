package com.apep.cleaningbuddy;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class TaskDetailsActivity extends AppCompatActivity {

    private TextView taskNameTextView;
    private TextView intervalTextView;
    private TextView roomTextView;
    private TextView assignedUserTextView;
    private TextView descriptionTextView;
    private Button deleteButton;
    private Button backButton;
    private Button historyButton;
    private Button editButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        taskNameTextView = findViewById(R.id.activity_task_details_item_taskName);
        intervalTextView = findViewById(R.id.activity_task_details_item_interval);
        roomTextView = findViewById(R.id.activity_task_details_item_room);
        assignedUserTextView = findViewById(R.id.activity_task_details_item_assignedUser);
        descriptionTextView = findViewById(R.id.activity_task_details_item_description);
        deleteButton = findViewById(R.id.activity_task_details_item_deleteButton);
        backButton = findViewById(R.id.activity_task_details_item_backButton);
        historyButton = findViewById(R.id.activity_task_details_item_archiveButton);
        editButton = findViewById(R.id.activity_task_details_item_editButton);

        // Get data from intent
        Intent intent = getIntent();
        String taskName = intent.getStringExtra("TASK_NAME");
        String interval = intent.getStringExtra("INTERVAL");
        String room = intent.getStringExtra("ROOM");
        String assignedUser = intent.getStringExtra("ASSIGNED_USER");
        String description = intent.getStringExtra("DESCRIPTION");

        // Set data to views
        taskNameTextView.setText(taskName);
        intervalTextView.setText(interval);
        roomTextView.setText(room);
        assignedUserTextView.setText(assignedUser);
        descriptionTextView.setText(description);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteConfirmationDialog(taskName);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(TaskDetailsActivity.this, TaskHistoryActivity.class);
                startActivity(historyIntent);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editIntent = new Intent(TaskDetailsActivity.this, EditTaskActivity.class);
                editIntent.putExtra("TASK_NAME", taskName);
                editIntent.putExtra("INTERVAL", interval);
                if ("Custom".equals(interval)) {
                    String customIntervalType = "Days"; // Retrieve this from the database or source
                    String customIntervalValue = "13"; // Retrieve this from the database or source
                    editIntent.putExtra("CUSTOM_INTERVAL_TYPE", customIntervalType);
                    editIntent.putExtra("CUSTOM_INTERVAL_VALUE", customIntervalValue);
                }
                editIntent.putExtra("DESCRIPTION", description);
                editIntent.putExtra("ROOM", room);
                editIntent.putExtra("ASSIGNED_USER", assignedUser);
                startActivity(editIntent);
            }
        });
    }

    private void showDeleteConfirmationDialog(final String taskName) {
        new AlertDialog.Builder(this)
                .setTitle("Delete task")
                .setMessage("Are you sure you want to delete '" + taskName + "'?")
                .setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle task deletion logic here
                        deleteTask(taskName);
                    }
                })
                .setNegativeButton("CANCEL", null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void deleteTask(String taskName) {
        // Implement task deletion logic here
        // For now, just show a Toast message and go back to the previous activity
        Toast.makeText(this, "Task '" + taskName + "' deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}

