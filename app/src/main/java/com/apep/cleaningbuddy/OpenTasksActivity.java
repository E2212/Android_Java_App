package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OpenTasksActivity extends BaseActivity {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_tasks);
        addNavbarListeners();

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
    }
}
