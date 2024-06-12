package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.TaskAdapter;
import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;

import java.util.List;

public class AllTasksActivity extends BaseActivity implements OnTaskClickListener {

    private RecyclerView recyclerView;
    private List<Task> allTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);
        addNavbarListeners();

        allTasks = Task.getAll(this); // Fetch all tasks from the database
        recyclerView = findViewById(R.id.all_tasks_list_rv);
        TaskAdapter adapter = new TaskAdapter(allTasks, this, false);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button yourTasksButton = findViewById(R.id.allTasks_yourTasks_btn_id);
        Button openTasksButton = findViewById(R.id.allTasks_openTasks_btn_id);
        Button allTasksButton = findViewById(R.id.allTasks_allTasks_btn_id);
        Button addButton = findViewById(R.id.allTasks_add_btn_id);

        yourTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(AllTasksActivity.this, TasksActivity.class);
            startActivity(intent);
        });

        openTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(AllTasksActivity.this, OpenTasksActivity.class);
            startActivity(intent);
        });

        allTasksButton.setOnClickListener(v -> {
            Toast.makeText(AllTasksActivity.this, "You are already in All Tasks", Toast.LENGTH_SHORT).show();
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(AllTasksActivity.this, NewTaskActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onTaskClick(Task task) {
        // Handle task click
    }
}
