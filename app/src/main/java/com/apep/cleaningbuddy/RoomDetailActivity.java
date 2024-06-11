package com.apep.cleaningbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.RoomAdapter;
import com.apep.cleaningbuddy.adapters.TaskAdapter;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;
import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.models.Task;

public class RoomDetailActivity extends AppCompatActivity implements OnTaskClickListener {

    private Room room;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);

        this.room = Room.getRoom(this, getIntent().getIntExtra("ROOM_ID", 0));
        TextView title = findViewById(R.id.tv_title);
        title.setText(room.getName());

        recyclerView = findViewById(R.id.tasks_list_rv);
        TaskAdapter adapter = new TaskAdapter(room.getTasks(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button editRoomButton = findViewById(R.id.btn_edit_room);
        editRoomButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomActivity.class);
            intent.putExtra("ROOM_ID", room.getId());
            startActivity(intent);
            finish();
        });

        Button removeRoomButton = findViewById(R.id.btn_remove_room);
        removeRoomButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.dialog_room_delete_title))
                    .setMessage(getString(R.string.dialog_room_delete_message, room.getName()))
                    .setPositiveButton(getString(R.string.dialog_btn_delete), (dialog, i) -> {
                        Room.deleteRoom(this, room);
                        Intent intent = new Intent(this, RoomsActivity.class);
                        startActivity(intent);
                        finish();
                    })
                    .setNegativeButton(getString(R.string.dialog_btn_cancel), (dialog, i) -> {
                        dialog.dismiss();
                    })
                    .show();
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
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onTaskClick(Task task) {
        // Geen actie ondernemen, de taken kunnen alleen als overzicht bekeken worden.
    }
}