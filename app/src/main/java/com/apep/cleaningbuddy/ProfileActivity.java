package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apep.cleaningbuddy.models.Language;
import com.apep.cleaningbuddy.models.Theme;
import com.apep.cleaningbuddy.models.User;

import java.util.Arrays;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        User loggedInUser = User.getLoggedInUser();
        TextView tvUsername = findViewById(R.id.tv_username);
        tvUsername.setText(loggedInUser.getUsername());

        Spinner languageSpinner = findViewById(R.id.spinner_language);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Language.getDisplayNames(this));
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);
        languageSpinner.setSelection(Language.getPosition(this, loggedInUser.getLanguage()));

        Spinner themeSpinner = findViewById(R.id.spinner_theme);
        ArrayAdapter<String> themeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Theme.getDisplayNames(this));
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        themeSpinner.setAdapter(themeAdapter);
        themeSpinner.setSelection(Theme.getPosition(this, loggedInUser.getTheme()));

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loggedInUser.setLanguage(Language.values()[position]);
                User.updateUser(getApplicationContext(), loggedInUser);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        themeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loggedInUser.setTheme(Theme.values()[position]);
                User.updateUser(getApplicationContext(), loggedInUser);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button logoutButton = findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            User.logout();
            finish();
        });

        Button roomsButton = findViewById(R.id.btn_rooms);
        roomsButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomsActivity.class);
            startActivity(intent);
            finish();
        });

        Button tasksButton = findViewById(R.id.btn_tasks);
        tasksButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, TasksActivity.class);
            startActivity(intent);
            finish();
        });

        Button profileButton = findViewById(R.id.btn_profile);
        profileButton.setOnClickListener(v -> {
            // Current Activity, no action needed
        });
    }
}
