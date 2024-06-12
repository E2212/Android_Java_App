package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.utils.InputValidations;

public class RoomActivity extends BaseActivity {

    private Room room;
    private TextView etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        addNavbarListeners();

        int roomId = getIntent().getIntExtra("ROOM_ID", 0);
        if (roomId != 0) {
            this.room = Room.getRoom(this, roomId);
        }

        TextView title = findViewById(R.id.tv_title);
        etName = findViewById(R.id.et_room_name);
        if (room != null) {
            title.setText(getString(R.string.room_edit_title));
            etName.setText(room.getName());
        } else {
            title.setText(getString(R.string.room_add_title));
        }
    }
    
    private void addEditButtonListeners() {
        Button cancelButton = findViewById(R.id.btn_cancel_edit);
        cancelButton.setOnClickListener(v -> {
            Intent intent;
            if (room != null) {
                intent = new Intent(this, RoomDetailActivity.class);
                intent.putExtra("ROOM_ID", room.getId());
            } else {
                intent = new Intent(this, RoomsActivity.class);
            }

            startActivity(intent);
            finish();
        });


        Button saveButton = findViewById(R.id.btn_save_edit);
        saveButton.setOnClickListener(v -> {
            InputValidations validator = new InputValidations(this);
            if (validator.validateNotEmpty(etName.getText().toString(), getString(R.string.error_room_name))) {
                Intent intent;
                if (room != null) {
                    room.setName(etName.getText().toString());
                    Room.updateRoom(this, room);
                    intent = new Intent(this, RoomDetailActivity.class);
                    intent.putExtra("ROOM_ID", room.getId());
                } else {
                    Room room = new Room();
                    this.room = room;
                    room.setName(etName.getText().toString());
                    Room.addRoom(this, room);
                    intent = new Intent(this, RoomsActivity.class);
                }

                startActivity(intent);
                finish();
            } else {
                etName.setError(validator.getErrors());
            }
        });
    }
}