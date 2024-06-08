package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apep.cleaningbuddy.database.Database;
import com.apep.cleaningbuddy.models.User;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);

        Button signupButton = findViewById(R.id.btn_sign_up);
        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        Button loginButton = findViewById(R.id.btn_login);
        loginButton.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (validateCredentials(username, password)) {
                Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean validateCredentials(String username, String password) {
        User user = Database.getDatabase(this).userDao().getUserByUsername(username);
        return user != null && user.getPassword().equals(password);
    }
}
