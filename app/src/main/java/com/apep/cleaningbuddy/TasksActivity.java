package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.OpenTaskAdapter;
import com.apep.cleaningbuddy.adapters.RoomAdapter;
import com.apep.cleaningbuddy.adapters.TaskAdapter;
import com.apep.cleaningbuddy.interfaces.OnOpenTaskClickListener;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;
import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.models.Task;

import java.util.List;

public class TasksActivity extends BaseActivity implements OnOpenTaskClickListener {

    private Button yourTasksButton;
    private Button openTasksButton;
    private Button allTasksButton;
    private Button addButton;
    private RecyclerView recyclerView;
    private List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);
        addNavbarListeners();

        tasks = Task.getAll(this);
        recyclerView = findViewById(R.id.open_tasks_list_rv);
        OpenTaskAdapter adapter = new OpenTaskAdapter(tasks,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    @Override
    public void onTaskClick(Task task) {
        // TODO: afvinken
    }
}
