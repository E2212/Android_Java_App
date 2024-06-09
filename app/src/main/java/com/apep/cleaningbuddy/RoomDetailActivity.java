package com.apep.cleaningbuddy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.apep.cleaningbuddy.models.Room;

public class RoomDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_room_detail);


        Room room = Room.getRoom(this, getIntent().getIntExtra("ROOM_ID", 0));

        TextView tv = findViewById(R.id.test_field);
        tv.setText(room.getName());
    }
}