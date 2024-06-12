package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AllTasksActivity extends BaseActivity {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        addNavbarListeners();

        yourTasksButton = findViewById(R.id.allTasks_yourTasks_btn_id);
        openTasksButton = findViewById(R.id.allTasks_openTasks_btn_id);
        allTasksButton = findViewById(R.id.allTasks_allTasks_btn_id);
        addButton = findViewById(R.id.allTasks_add_btn_id);

        yourTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to TasksActivity
                Intent intent = new Intent(AllTasksActivity.this, YourTasksActivity.class);
                startActivity(intent);
            }
        });

        openTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to OpenTasksActivity
                Intent intent = new Intent(AllTasksActivity.this, OpenTasksActivity.class);
                startActivity(intent);
            }
        });

        allTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle "All tasks" button click
                Toast.makeText(AllTasksActivity.this, "You are already in All Tasks", Toast.LENGTH_SHORT).show();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to NewTaskActivity
                Intent intent = new Intent(AllTasksActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }
}
