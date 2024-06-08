package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(v -> onBackPressed());

        Button logoutButton = findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        Button roomsButton = findViewById(R.id.btn_rooms);
        roomsButton.setOnClickListener(v -> {
            // Placeholder for navigating to RoomsActivity
        });

        Button tasksButton = findViewById(R.id.btn_tasks);
        tasksButton.setOnClickListener(v -> {
            // Placeholder for navigating to TasksActivity
        });

        Button profileButton = findViewById(R.id.btn_profile);
        profileButton.setOnClickListener(v -> {
            // Current Activity, no action needed
        });
    }
}
