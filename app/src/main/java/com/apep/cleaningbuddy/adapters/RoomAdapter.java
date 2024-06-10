package com.apep.cleaningbuddy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.R;
import com.apep.cleaningbuddy.interfaces.OnRoomClickListener;
import com.apep.cleaningbuddy.models.Room;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder> {
    private final List<Room> data;
    private final OnRoomClickListener listener;

    public RoomAdapter(List<Room> data, OnRoomClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.room_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Room room = data.get(position);
        holder.bind(room, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView roomIdTf;
        private final TextView roomNameTf;
        private final TextView roomTaskCountTf;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roomIdTf = itemView.findViewById(R.id.room_id_tf);
            roomNameTf = itemView.findViewById(R.id.room_name_tf);
            roomTaskCountTf = itemView.findViewById(R.id.room_task_count_tf);
        }

        public void bind(Room room, OnRoomClickListener listener) {
            String id = "R-" + room.getId().toString();
            roomIdTf.setText(id);
            roomNameTf.setText(room.getName());
            roomTaskCountTf.setText(String.valueOf(room.getTasks().size()));

            itemView.setOnClickListener(v -> listener.onRoomClick(room));
        }

    }
}
