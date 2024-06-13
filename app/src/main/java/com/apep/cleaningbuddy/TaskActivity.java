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

import com.apep.cleaningbuddy.models.CustomInterval;
import com.apep.cleaningbuddy.models.Interval;
import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.models.Task;
import com.apep.cleaningbuddy.models.User;
import com.apep.cleaningbuddy.utils.InputValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TaskActivity extends BaseActivity {

    private EditText taskNameEditText;
    private Spinner spInterval;
    private TextView tvCustomIntervalLabel;
    private LinearLayout containerCustomInterval;
    private Spinner spCustomIntervalType;
    private EditText etCustomInterval;
    private EditText etDescription;
    private Spinner spRoom;
    private Spinner spAssignedUser;
    private Task task;
    private EditText etName;
    private final List<User> users = new ArrayList<>();
    private final List<Room> rooms = new ArrayList<>();
    private boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        addNavbarListeners();
        addEditButtonListeners();

        users.addAll(User.getAll(this));
        rooms.addAll(Room.getAll(this));

        int taskId = getIntent().getIntExtra("TASK_ID", 0);
        if (taskId != 0) {
            edit = true;
            this.task = Task.getTask(this, taskId);
        }

        TextView title = findViewById(R.id.tv_title);
        etName = findViewById(R.id.et_task_name);
        spInterval = findViewById(R.id.sp_task_interval);
        tvCustomIntervalLabel = findViewById(R.id.label_task_custom_interval);
        containerCustomInterval = findViewById(R.id.container_task_custom_interval);
        spCustomIntervalType = findViewById(R.id.sp_task_custom_interval_type);
        etCustomInterval = findViewById(R.id.et_task_custom_interval);
        etDescription = findViewById(R.id.et_task_description);
        spRoom = findViewById(R.id.sp_task_room);
        spAssignedUser = findViewById(R.id.sp_task_user);

        populateIntervalSpinner();
        populateCustomIntervalTypeSpinner();
        populateRoomSpinner();
        populateAssignedUserSpinner();

        if (rooms.isEmpty()) {
            spRoom.setVisibility(View.GONE);
        }

        if (users.isEmpty()) {
            spAssignedUser.setVisibility(View.GONE);
        }

        if (task != null) {
            title.setText(getString(R.string.task_edit_title));
            etName.setText(task.getName());
            etDescription.setText(task.getDescription());

            Interval interval = Interval.getType(task.getInterval());
            spInterval.setSelection(Interval.getPosition(this, interval));

            if (interval == Interval.CUSTOM) {
                CustomInterval customInterval = CustomInterval.getType(task.getInterval());
                spCustomIntervalType.setSelection(CustomInterval.getPosition(this, customInterval));
                etCustomInterval.setText(String.valueOf(CustomInterval.getCustomIntervalAmount(task.getInterval())));
            }

            if (task.getUser() != null) {
                spAssignedUser.setSelection(users.indexOf(task.getUser()));
            }

            if (task.getRoom() != null) {
                int index = Room.getListIndex(rooms, task.getRoom());
                spRoom.setSelection(index);
            }
        } else {
            title.setText(getString(R.string.task_add_title));
        }
    }

    private void addEditButtonListeners() {
        Button cancelButton = findViewById(R.id.btn_cancel_edit);
        cancelButton.setOnClickListener(v -> {
            Intent intent;
            if (task != null) {
                intent = new Intent(this, TaskDetailsActivity.class);
                intent.putExtra("TASK_ID", task.getId());
            } else {
                if (edit) {
                    intent = new Intent(this, AllTasksActivity.class);
                } else {
                    intent = new Intent(this, YourTasksActivity.class);
                }
            }

            startActivity(intent);
            finish();
        });


        Button saveButton = findViewById(R.id.btn_save_edit);
        saveButton.setOnClickListener(v -> {
            save();
        });
    }

    private void save() {
        if (!edit) {
            task = new Task();
        }

        boolean errors = false;
        InputValidations validator = new InputValidations(this);
        if (!validator.validateNotEmpty(etName.getText().toString(), getString(R.string.error_task_name))) {
            etName.setError(validator.getErrors());
            errors = true;
        }

        task.setName(etName.getText().toString());
        task.setDescription(etDescription.getText().toString());

        int resourceId = Interval.getResourceIds()[spInterval.getSelectedItemPosition()];
        Interval interval = Interval.fromResourceId(resourceId);

        int intervalAmount;
        if (interval == Interval.DAILY) {
            intervalAmount = 1;
        } else if (interval == Interval.WEEKLY) {
            intervalAmount = 7;
        } else {
            int customIntervalResourceId = CustomInterval.getResourceIds()[spCustomIntervalType.getSelectedItemPosition()];
            CustomInterval customInterval = CustomInterval.fromResourceId(customIntervalResourceId);


            if (!validator.validateNotEmpty(etCustomInterval.getText().toString(), getString(R.string.error_custom_interval_name))) {
                etName.setError(validator.getErrors());
                errors = true;
                intervalAmount = 0;
            } else if (!validator.validateNumeric(etCustomInterval.getText().toString(), getString(R.string.error_custom_interval_name))) {
                etName.setError(validator.getErrors());
                errors = true;
                intervalAmount = 0;
            } else {
                String inputCustomInterval = etCustomInterval.getText().toString();
                int customIntervalAmount = Integer.parseInt(inputCustomInterval);
                if (customInterval == CustomInterval.MONTHS) {
                    intervalAmount = customIntervalAmount * 30;
                } else if (customInterval == CustomInterval.WEEKS) {
                    intervalAmount = customIntervalAmount * 7;
                } else {
                    intervalAmount = customIntervalAmount;
                }
            }
        }

        task.setInterval(intervalAmount);

        if (!rooms.isEmpty()) {
            Room room = (Room) spRoom.getSelectedItem();
            task.setRoomId(room.getId());
        }

        if (!users.isEmpty()) {
            User user = (User) spAssignedUser.getSelectedItem();
            task.setUserId(user.getId());
        }

        Intent intent;
        if (edit & !errors) {
            Task.updateTask(this, task);
            intent = new Intent(this, TaskDetailsActivity.class);
            intent.putExtra("TASK_ID", task.getId());
        } else {
            Task.addTask(this, task);
            intent = new Intent(this, YourTasksActivity.class);
        }

        if (!errors) {
            startActivity(intent);
            finish();
        }
    }


    private void populateIntervalSpinner() {
        spInterval = findViewById(R.id.sp_task_interval);
        ArrayAdapter<String> intervalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Interval.getDisplayNames(this));
        intervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spInterval.setAdapter(intervalAdapter);

        spInterval.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int resourceId = Interval.getResourceIds()[spInterval.getSelectedItemPosition()];
                if (Objects.equals(Interval.fromResourceId(resourceId), Interval.CUSTOM)) {
                    tvCustomIntervalLabel.setVisibility(View.VISIBLE);
                    containerCustomInterval.setVisibility(View.VISIBLE);
                } else {
                    tvCustomIntervalLabel.setVisibility(View.GONE);
                    containerCustomInterval.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvCustomIntervalLabel.setVisibility(View.GONE);
                containerCustomInterval.setVisibility(View.GONE);
            }
        });
    }

    private void populateCustomIntervalTypeSpinner() {
        spCustomIntervalType = findViewById(R.id.sp_task_custom_interval_type);
        ArrayAdapter<String> customIntervalAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CustomInterval.getDisplayNames(this));
        customIntervalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCustomIntervalType.setAdapter(customIntervalAdapter);
    }


    private void populateRoomSpinner() {
        spRoom = findViewById(R.id.sp_task_room);
        ArrayAdapter<Room> roomAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRoom.setAdapter(roomAdapter);
    }

    private void populateAssignedUserSpinner() {
        spAssignedUser = findViewById(R.id.sp_task_user);
        ArrayAdapter<User> userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, users);
        userAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAssignedUser.setAdapter(userAdapter);
    }

}
