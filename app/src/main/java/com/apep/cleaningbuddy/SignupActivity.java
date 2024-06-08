package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.apep.cleaningbuddy.models.User;
import com.apep.cleaningbuddy.utils.MethodsValidations;

public class SignupActivity extends AppCompatActivity {
    private MethodsValidations validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        validator = new MethodsValidations(this);

        EditText etUsername = findViewById(R.id.et_username);
        EditText etPassword = findViewById(R.id.et_password);
        EditText etRepeatPassword = findViewById(R.id.et_repeat_password);
        Spinner spinnerLanguage = findViewById(R.id.spinner_language);
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this, R.array.language_options, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapterLanguage);

        Button signupButton = findViewById(R.id.btn_signup);
        signupButton.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String repeatPassword = etRepeatPassword.getText().toString().trim();

            if (!validator.validateLength(username, "Username", 4)) {
                showError(validator.getError());
                return;
            }
            if (!validator.validateNotEmpty(username, "Username")) {
                showError(validator.getError());
                return;
            }
            if (!validator.validatePasswordStrength(password, "Password")) {
                showError(validator.getError());
                return;
            }
            if (!password.equals(repeatPassword)) {
                showError(getString(R.string.error_password_mismatch));
                return;
            }

            User newUser = new User();
            newUser.setUsername(username);
            newUser.setPassword(password);
            newUser.setLanguage(spinnerLanguage.getSelectedItem().toString());

            User.addUser(this,newUser);

            Toast.makeText(this, "Signup successful!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
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
