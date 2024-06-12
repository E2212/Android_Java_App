package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.apep.cleaningbuddy.exceptions.UserNotFoundException;
import com.apep.cleaningbuddy.models.User;
import com.apep.cleaningbuddy.utils.InputValidations;

public class SignupActivity extends BaseActivity {
    private InputValidations validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        validator = new InputValidations(this);

        EditText etUsername = findViewById(R.id.signup_username_et_id);
        EditText etPassword = findViewById(R.id.signup_password_tv_id);
        EditText etRepeatPassword = findViewById(R.id.signup_repeat_password_tv_id);

        Button signupButton = findViewById(R.id.btn_signup_id);
        signupButton.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String repeatPassword = etRepeatPassword.getText().toString().trim();

            boolean errors = false;
            if (!validator.validateLength(username, "Username", 4)) {
                etUsername.setError(validator.getErrors());
                errors = true;
            }
            if (!validator.validateNotEmpty(username, "Username")) {
                etUsername.setError(validator.getErrors());
                errors = true;
            }

            boolean available_username;
            try {
                User.getUserByUsername(this, username);
                etUsername.setError(getString(R.string.error_username_unavailable_text));
                available_username = false;
                errors = true;
            } catch (UserNotFoundException e) {
                available_username = true;
            }

            if (available_username) {
                if (!validator.validatePasswordStrength(password, "Password")) {
                    etPassword.setError(validator.getErrors());
                    errors = true;
                } else if (!password.equals(repeatPassword)) {
                    etRepeatPassword.setError(getString(R.string.error_password_mismatch_text));
                    errors = true;
                }
            }

            if (!errors) {
                User newUser = new User();
                newUser.setUsername(username);
                newUser.setHashedPassword(password.getBytes());
                User.addUser(this, newUser);

                Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button cancelButton = findViewById(R.id.signup_cancel_btn_id);
        cancelButton.setOnClickListener(v -> finish());
    }
}
