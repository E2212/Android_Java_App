package com.apep.cleaningbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        // Get the intent that started this activity and extract the task details
        String taskName = getIntent().getStringExtra("TASK_NAME");
        String interval = getIntent().getStringExtra("INTERVAL");
        String room = getIntent().getStringExtra("ROOM");
        String assignedUser = getIntent().getStringExtra("ASSIGNED_USER");
        String description = getIntent().getStringExtra("DESCRIPTION");

        // Capture the layout's TextViews and set the string as their text
        TextView taskNameTextView = findViewById(R.id.activity_task_details_item_taskName);
        TextView intervalTextView = findViewById(R.id.activity_task_details_item_interval);
        TextView roomTextView = findViewById(R.id.activity_task_details_item_room);
        TextView assignedUserTextView = findViewById(R.id.activity_task_details_item_assignedUser);
        TextView descriptionTextView = findViewById(R.id.activity_task_details_item_description);

        taskNameTextView.setText(taskName);
        intervalTextView.setText(interval);
        roomTextView.setText(room);
        assignedUserTextView.setText(assignedUser);
        descriptionTextView.setText(description);

        // Back button logic
        Button backButton = findViewById(R.id.activity_task_details_item_backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and return to the previous one
                finish();
            }
        });
    }
}
