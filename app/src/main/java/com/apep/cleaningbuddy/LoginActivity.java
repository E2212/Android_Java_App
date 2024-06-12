package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.apep.cleaningbuddy.database.DatabaseSeeder;
import com.apep.cleaningbuddy.exceptions.UserNotFoundException;
import com.apep.cleaningbuddy.models.User;

public class LoginActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsername = findViewById(R.id.signup_username_et_id);
        EditText etPassword = findViewById(R.id.signup_password_tv_id);

        Button signupButton = findViewById(R.id.login_sign_up_btn_id);
        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        Button loginButton = findViewById(R.id.login_login_btn_id);
        loginButton.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            boolean valid;
            try {
                User user = User.getUserByUsername(this, username);
                valid = user.checkUserPassword(password.getBytes());
            } catch (UserNotFoundException e) {
                valid = false;
            }

            if (valid) {
                Intent intent = new Intent(LoginActivity.this, YourTasksActivity.class);
                startActivity(intent);
                finish();
            } else {
                etPassword.setError(getString(R.string.error_invalid_username_password_text));
            }
        });

        DatabaseSeeder databaseSeeder = new DatabaseSeeder();
        databaseSeeder.seedDatabase(this);
    }
}
