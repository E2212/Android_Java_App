package com.apep.cleaningbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.adapters.TaskAdapter;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;
import com.apep.cleaningbuddy.models.Room;
import com.apep.cleaningbuddy.models.Task;

public class RoomDetailActivity extends BaseActivity implements OnTaskClickListener {

    private Room room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        addNavbarListeners();

        this.room = Room.getRoom(this, getIntent().getIntExtra("ROOM_ID", 0));
        TextView title = findViewById(R.id.tv_title);
        title.setText(room.getName());

        RecyclerView recyclerView = findViewById(R.id.tasks_list_rv);
        TaskAdapter adapter = new TaskAdapter(room.getTasks(), this, this);
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
                    .setMessage(getString(R.string.dialog_delete_message, room.getName()))
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
    }

    @Override
    public void onTaskClick(Task task) {
        // Geen actie ondernemen, de taken kunnen alleen als overzicht bekeken worden.
    }
}