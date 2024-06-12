package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.RoomAdapter;
import com.apep.cleaningbuddy.interfaces.OnRoomClickListener;
import com.apep.cleaningbuddy.models.Room;

import java.util.List;

public class RoomsActivity extends BaseActivity implements OnRoomClickListener {
    private RecyclerView recyclerView;
    private List<Room> rooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms);
        addNavbarListeners();

        rooms = Room.getAll(this);
        recyclerView = findViewById(R.id.rooms_list_rv);
        RoomAdapter adapter = new RoomAdapter(rooms, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button addRoomButton = findViewById(R.id.btn_add_room);
        addRoomButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onRoomClick(Room room) {
        Intent intent = new Intent(this, RoomDetailActivity.class);
        intent.putExtra("ROOM_ID", room.getId());
        startActivity(intent);
        finish();
    }
}