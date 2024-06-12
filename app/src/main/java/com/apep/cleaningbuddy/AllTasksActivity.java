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
        addNavbarListeners();
        addTabListeners();
        addNewTaskButton();

        allTasks = Task.getAll(AllTasksActivity.this);
        recyclerView = findViewById(R.id.all_tasks_list_rv);
        TaskAdapter adapter = new TaskAdapter(allTasks, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void addNewTaskButton() {
        Button addButton = findViewById(R.id.task_add_btn_id);
        addButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TaskActivity.class);
            startActivity(intent);
        });
    }

    private void addTabListeners() {
        Button yourTasksButton = findViewById(R.id.task_your_task_tab_id);
        yourTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, YourTasksActivity.class);
            startActivity(intent);
            finish();
        });

        Button openTasksButton = findViewById(R.id.tasks_open_task_tab_id);
        openTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, OpenTasksActivity.class);
            startActivity(intent);
            finish();
        });

        Button allTasksButton = findViewById(R.id.tasks_all_tasks_tab_id);
        allTasksButton.setOnClickListener(v -> {
            //
        });
    }

    @Override
    public void onTaskClick(Task task) {
        Intent intent = new Intent(this, TaskDetailsActivity.class);
        intent.putExtra("TASK_ID", task.getId());
        startActivity(intent);
        finish();
    }
}
