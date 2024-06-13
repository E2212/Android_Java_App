package com.apep.cleaningbuddy;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.apep.cleaningbuddy.models.CustomInterval;
import com.apep.cleaningbuddy.models.Interval;
import com.apep.cleaningbuddy.models.Task;


public class TaskDetailsActivity extends BaseActivity {
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        addNavbarListeners();

        this.task = Task.getTask(this, getIntent().getIntExtra("TASK_ID", 0));

        TextView tvTaskName = findViewById(R.id.tv_title);
        tvTaskName.setText(task.getName());

        TextView tvRoom = findViewById(R.id.activity_task_details_item_room);
        if (task.getRoomId() != null) {
            tvRoom.setText(task.getRoom().getName());
        } else {
            tvRoom.setText(getString(R.string.unassigned));
        }

        TextView tvUser = findViewById(R.id.activity_task_details_item_assignedUser);
        if (task.getUserId() != null) {
            tvUser.setText(task.getUser().getUsername());
        } else {
            tvUser.setText(getString(R.string.unassigned));
        }

        TextView tvDescription = findViewById(R.id.activity_task_details_item_description);
        tvDescription.setText(task.getDescription());

        Interval interval = Interval.getType(task.getInterval());
        TextView tvInterval = findViewById(R.id.activity_task_details_item_interval);
        if (interval != Interval.CUSTOM) {
            tvInterval.setText(getString(interval.getResourceId()));
        } else {
            int intervalAmount = CustomInterval.getCustomIntervalAmount(task.getInterval());
            CustomInterval customInterval = CustomInterval.getType(task.getInterval());
            String string = intervalAmount + " " + getString(customInterval.getResourceId());
            tvInterval.setText(string);
        }

        Button historyButton = findViewById(R.id.btn_history_task);
        historyButton.setOnClickListener(v -> {
            Intent intent = new Intent(TaskDetailsActivity.this, TaskHistoryActivity.class);
            intent.putExtra("TASK_ID", task.getId());
            startActivity(intent);
        });

        Button editTaskButton = findViewById(R.id.btn_edit_task);
        editTaskButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TaskActivity.class);
            intent.putExtra("TASK_ID", task.getId());
            startActivity(intent);
            finish();
        });

        Button removeRoomButton = findViewById(R.id.btn_remove_task);
        removeRoomButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.dialog_task_delete_title))
                    .setMessage(getString(R.string.dialog_delete_message, task.getName()))
                    .setPositiveButton(getString(R.string.dialog_btn_delete), (dialog, i) -> {
                        Task.deleteTask(this, task);
                        Intent intent = new Intent(this, AllTasksActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton(getString(R.string.dialog_btn_cancel), (dialog, i) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
    }
}

