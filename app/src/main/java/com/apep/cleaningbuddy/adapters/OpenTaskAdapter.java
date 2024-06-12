package com.apep.cleaningbuddy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.R;
import com.apep.cleaningbuddy.interfaces.OnOpenTaskClickListener;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;
import com.apep.cleaningbuddy.models.Task;

import java.util.List;

public class OpenTaskAdapter extends RecyclerView.Adapter<OpenTaskAdapter.ViewHolder> {
    private final List<Task> data;
    private final OnOpenTaskClickListener listener;

    public OpenTaskAdapter(List<Task> data, OnOpenTaskClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.open_task_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task room = data.get(position);
        holder.bind(room, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskIdTf;
        private final TextView taskNameTf;
        private final TextView taskAssignedTf;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskIdTf = itemView.findViewById(R.id.task_id_tf);
            taskNameTf = itemView.findViewById(R.id.task_name_tf);
            taskAssignedTf = itemView.findViewById(R.id.task_assigned_tf);
        }

        public void bind(Task task, OnOpenTaskClickListener listener) {
            String id = "T-" + task.getId().toString();
            taskIdTf.setText(id);
            taskNameTf.setText(task.getName());
            taskAssignedTf.setText((task.getUser() == null ? "Unassigned" : task.getUser().getUsername()));

            itemView.setOnClickListener(v -> listener.onTaskClick(task));
        }

    }
}
