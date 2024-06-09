package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AllTasksActivity extends AppCompatActivity {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        yourTasksButton = findViewById(R.id.allTasks_yourTasks_btn_id);
        openTasksButton = findViewById(R.id.allTasks_openTasks_btn_id);
        allTasksButton = findViewById(R.id.allTasks_allTasks_btn_id);
        addButton = findViewById(R.id.allTasks_add_btn_id);

        yourTasksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to TasksActivity
                Intent intent = new Intent(AllTasksActivity.this, TasksActivity.class);
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
                // Handle "Add" button click
                Toast.makeText(AllTasksActivity.this, "Add button clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // Setup click listeners for table rows
        setupTaskRowClickListener(R.id.allTasks_task1_tr_id, "Curtain cleaning", "Weekly", "Room 6", "You", "Curtains must be washed at 60°C");
        setupTaskRowClickListener(R.id.allTasks_task2_tr_id, "Toilet cleaning", "Daily", "Room 5", "Ensar", "Clean the toilets daily");
        setupTaskRowClickListener(R.id.allTasks_task3_tr_id, "Carpet cleaning", "Monthly", "Room 4", "Paco", "Vacuum and clean carpets monthly");
        setupTaskRowClickListener(R.id.allTasks_task4_tr_id, "Bedding", "Weekly", "Room 3", "You", "Change bedding weekly");
        setupTaskRowClickListener(R.id.allTasks_task5_tr_id, "Electronics", "Weekly", "Room 2", "Khalid", "Dust and clean electronics weekly");
    }

    private void setupTaskRowClickListener(int rowId, final String taskName, final String interval, final String room, final String assignedUser, final String description) {
        TableRow taskRow = findViewById(rowId);
        taskRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllTasksActivity.this, TaskDetailsActivity.class);
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
