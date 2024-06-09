package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class EditTaskActivity extends AppCompatActivity {

    private EditText taskNameEditText;
    private Spinner intervalSpinner;
    private TextView customIntervalLabel;
    private LinearLayout customIntervalContainer;
    private Spinner customIntervalTypeSpinner;
    private EditText customIntervalValueEditText;
    private EditText descriptionEditText;
    private Spinner roomSpinner;
    private Spinner assignedUserSpinner;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        taskNameEditText = findViewById(R.id.activity_edit_task_item_taskName);
        intervalSpinner = findViewById(R.id.activity_edit_task_item_interval);
        customIntervalLabel = findViewById(R.id.activity_edit_task_item_customIntervalLabel);
        customIntervalContainer = findViewById(R.id.activity_edit_task_item_customIntervalContainer);
        customIntervalTypeSpinner = findViewById(R.id.activity_edit_task_item_customIntervalType);
        customIntervalValueEditText = findViewById(R.id.activity_edit_task_item_customIntervalValue);
        descriptionEditText = findViewById(R.id.activity_edit_task_item_description);
        roomSpinner = findViewById(R.id.activity_edit_task_item_room);
        assignedUserSpinner = findViewById(R.id.activity_edit_task_item_assignedUser);
        cancelButton = findViewById(R.id.activity_edit_task_item_cancelButton);
        saveButton = findViewById(R.id.activity_edit_task_item_saveButton);

        // Populate spinners with data
        populateIntervalSpinner();
        populateCustomIntervalTypeSpinner();
        populateRoomSpinner();
        populateAssignedUserSpinner();

        // Handle interval spinner selection
        intervalSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedInterval = (String) parent.getItemAtPosition(position);
                if ("Custom".equals(selectedInterval)) {
                    customIntervalLabel.setVisibility(View.VISIBLE);
                    customIntervalContainer.setVisibility(View.VISIBLE);
                } else {
                    customIntervalLabel.setVisibility(View.GONE);
                    customIntervalContainer.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                customIntervalLabel.setVisibility(View.GONE);
                customIntervalContainer.setVisibility(View.GONE);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Go back to the previous activity
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle save task logic
                if (validateInputs()) {
                    saveTask();
                }
            }
        });

        // Get data from intent and set to views
        Intent intent = getIntent();
        String taskName = intent.getStringExtra("TASK_NAME");
        String interval = intent.getStringExtra("INTERVAL");
        String customIntervalType = intent.getStringExtra("CUSTOM_INTERVAL_TYPE");
        String customIntervalValue = intent.getStringExtra("CUSTOM_INTERVAL_VALUE");
        String description = intent.getStringExtra("DESCRIPTION");
        String room = intent.getStringExtra("ROOM");
        String assignedUser = intent.getStringExtra("ASSIGNED_USER");

        taskNameEditText.setText(taskName);
        intervalSpinner.setSelection(((ArrayAdapter<String>) intervalSpinner.getAdapter()).getPosition(interval));
        if ("Custom".equals(interval)) {
            customIntervalLabel.setVisibility(View.VISIBLE);
            customIntervalContainer.setVisibility(View.VISIBLE);
            customIntervalTypeSpinner.setSelection(((ArrayAdapter<String>) customIntervalTypeSpinner.getAdapter()).getPosition(customIntervalType));
            customIntervalValueEditText.setText(customIntervalValue);
        }
        descriptionEditText.setText(description);
        roomSpinner.setSelection(((ArrayAdapter<String>) roomSpinner.getAdapter()).getPosition(room));
        assignedUserSpinner.setSelection(((ArrayAdapter<String>) assignedUserSpinner.getAdapter()).getPosition(assignedUser));
    }

    private void populateIntervalSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.interval_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intervalSpinner.setAdapter(adapter);
    }

    private void populateCustomIntervalTypeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.custom_interval_type_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        customIntervalTypeSpinner.setAdapter(adapter);
    }

    private void populateRoomSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.room_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomSpinner.setAdapter(adapter);
    }

    private void populateAssignedUserSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        assignedUserSpinner.setAdapter(adapter);
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (taskNameEditText.getText().toString().trim().isEmpty()) {
            taskNameEditText.setError("Field is required");
            isValid = false;
        }

        if (intervalSpinner.getSelectedItem() == null) {
            TextView errorText = (TextView) intervalSpinner.getSelectedView();
            if (errorText != null) {
                errorText.setError("Field is required");
            }
            isValid = false;
        }

        if ("Custom".equals(intervalSpinner.getSelectedItem())) {
            if (customIntervalTypeSpinner.getSelectedItem() == null) {
                TextView errorText = (TextView) customIntervalTypeSpinner.getSelectedView();
                if (errorText != null) {
                    errorText.setError("Field is required");
                }
                isValid = false;
            }

            if (customIntervalValueEditText.getText().toString().trim().isEmpty()) {
                customIntervalValueEditText.setError("Field is required");
                isValid = false;
            }
        }

        if (roomSpinner.getSelectedItem() == null) {
            TextView errorText = (TextView) roomSpinner.getSelectedView();
            if (errorText != null) {
                errorText.setError("Field is required");
            }
            isValid = false;
        }

        if (assignedUserSpinner.getSelectedItem() == null) {
            TextView errorText = (TextView) assignedUserSpinner.getSelectedView();
            if (errorText != null) {
                errorText.setError("Field is required");
            }
            isValid = false;
        }

        return isValid;
    }

    private void saveTask() {
        // Implement task saving logic here
        Toast.makeText(this, "Task saved", Toast.LENGTH_SHORT).show();
        finish();
    }
}

