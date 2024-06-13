package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.OpenTaskAdapter;
import com.apep.cleaningbuddy.models.Task;

import java.util.List;

public class OpenTasksActivity extends BaseActivity {
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_tasks);
        addNavbarListeners();
        addTabListeners();
        addNewTaskButton();

        tasks = Task.getOpenTasks(OpenTasksActivity.this);
        RecyclerView recyclerView = findViewById(R.id.open_tasks_list_rv);
        OpenTaskAdapter adapter = new OpenTaskAdapter(tasks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button confirmBtn = findViewById(R.id.btn_completed_confirm);
        confirmBtn.setOnClickListener(v -> {
            List<Task> confirmedTasks = adapter.getCheckedTasks();
            if (!confirmedTasks.isEmpty()) {
                Toast.makeText(this, getString(R.string.confirm_completed_task_text, String.valueOf(confirmedTasks.size())), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.confirm_completed_none_task_text), Toast.LENGTH_SHORT).show();
            }
            Task.completeTasks(this, confirmedTasks);

            tasks.clear();
            tasks.addAll(Task.getOpenTasks(this));
            adapter.updateTasks(tasks);
        });
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
            //
        });

        Button allTasksButton = findViewById(R.id.tasks_all_tasks_tab_id);
        allTasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, AllTasksActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
