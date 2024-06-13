package com.apep.cleaningbuddy;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.TaskAdapter;
import com.apep.cleaningbuddy.adapters.TaskHistoryAdapter;
import com.apep.cleaningbuddy.models.CompletedTask;
import com.apep.cleaningbuddy.models.Task;

import java.util.List;

public class TaskHistoryActivity extends BaseActivity {

    private Task task;
    private boolean listAsc = false;
    private List<CompletedTask> taskHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history);
        addNavbarListeners();

        this.task = Task.getTask(this, getIntent().getIntExtra("TASK_ID", 0));
        taskHistory = task.getTaskHistory(this);

        RecyclerView recyclerView = findViewById(R.id.history_list_rv);
        TaskHistoryAdapter adapter = new TaskHistoryAdapter(taskHistory, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button backButton = findViewById(R.id.activity_task_history_item_backButton);
        backButton.setOnClickListener(v -> {
            finish();
        });

        View sortIcon = findViewById(R.id.sort_btn_id);
        View dateHeader = findViewById(R.id.history_date_header);
        dateHeader.setOnClickListener(v -> {
            taskHistory.clear();
            Resources res = getResources();
            if (listAsc) {
                sortIcon.setForeground(ResourcesCompat.getDrawable(res,R.drawable.sort_asc_button, null));
                taskHistory.addAll(Task.getDescTaskHistory(this, task.getId()));
                listAsc = false;
            } else {
                sortIcon.setForeground(ResourcesCompat.getDrawable(res,R.drawable.sort_desc_button, null));
                taskHistory.addAll(Task.getAscTaskHistory(this, task.getId()));
                listAsc = true;
            }
            adapter.notifyDataSetChanged();
        });
    }
}
