package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class OpenTasksActivity extends BaseActivity {
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.TaskAdapter;
import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;

import java.util.List;

public class OpenTasksActivity extends BaseActivity implements OnTaskClickListener {

    private RecyclerView recyclerView;
    private List<Task> openTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_tasks);
        addNavbarListeners();

        openTasks = Task.getOpenTasks(this); // Fetch open tasks from the database
        recyclerView = findViewById(R.id.open_tasks_list_rv);
        TaskAdapter adapter = new TaskAdapter(openTasks, this, true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button yourTasksButton = findViewById(R.id.openTasks_yourTasks_btn_id);
        Button openTasksButton = findViewById(R.id.openTasks_openTasks_btn_id);
        Button allTasksButton = findViewById(R.id.openTasks_allTasks_btn_id);
        Button addButton = findViewById(R.id.openTasks_add_btn_id);

        yourTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(OpenTasksActivity.this, TasksActivity.class);
            startActivity(intent);
        });

        openTasksButton.setOnClickListener(v -> {
            Toast.makeText(OpenTasksActivity.this, "You are already in Open Tasks", Toast.LENGTH_SHORT).show();
        });

        allTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(OpenTasksActivity.this, AllTasksActivity.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(OpenTasksActivity.this, NewTaskActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onTaskClick(Task task) {
        // Handle task click
    }
}
