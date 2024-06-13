package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.apep.cleaningbuddy.models.User;

public class ProfileActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addNavbarListeners();

        User loggedInUser = User.getLoggedInUser();
        TextView tvUsername = findViewById(R.id.profile_username_tv_id);
        tvUsername.setText(loggedInUser.getUsername());

        Button logoutButton = findViewById(R.id.profile_logout_btn_id);
        logoutButton.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            User.logout();
            finish();
        });
    }
}
