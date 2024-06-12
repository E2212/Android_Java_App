package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Standaard gedrag van de backbutton negeren.
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    protected void addNavbarListeners() {
        Button roomsButton = findViewById(R.id.btn_rooms);
        roomsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomsActivity.class);
            startActivity(intent);
            finish();
        });

        Button tasksButton = findViewById(R.id.btn_tasks);
        tasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, YourTasksActivity.class);
            startActivity(intent);
            finish();
        });

        Button profileButton = findViewById(R.id.btn_profile);
        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }
}