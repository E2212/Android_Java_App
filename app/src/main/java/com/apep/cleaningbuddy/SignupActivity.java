package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apep.cleaningbuddy.models.Language;
import com.apep.cleaningbuddy.models.Theme;
import com.apep.cleaningbuddy.models.User;
import com.apep.cleaningbuddy.utils.InputValidations;

public class SignupActivity extends AppCompatActivity {
    private InputValidations validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        validator = new InputValidations(this);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        EditText etRepeatPassword = findViewById(R.id.et_repeat_password);

        Spinner languageSpinner = findViewById(R.id.spinner_language);
        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Language.getDisplayNames(this));
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);

        Button signupButton = findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String repeatPassword = etRepeatPassword.getText().toString().trim();

            if (!validator.validateLength(username, "Username", 4)) {
                showError(validator.getErrors());
                return;
            }
            if (!validator.validateNotEmpty(username, "Username")) {
                showError(validator.getErrors());
                return;
            }
            if (!validator.validatePasswordStrength(password, "Password")) {
                showError(validator.getErrors());
                return;
            }
            if (!password.equals(repeatPassword)) {
                showError(getString(R.string.error_password_mismatch));
                return;
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setHashedPassword(password.getBytes());
            newUser.setTheme(Theme.DARK);

            int resourceId = Language.getResourceIds()[languageSpinner.getSelectedItemPosition()];
            newUser.setLanguage(Language.fromResourceId(resourceId));

            User.addUser(this, newUser);

            Intent intent = new Intent(SignupActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });

        Button cancelButton = findViewById(R.id.btn_cancel);
        cancelButton.setOnClickListener(v -> finish());
    }

    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
