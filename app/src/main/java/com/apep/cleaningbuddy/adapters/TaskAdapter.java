package com.apep.cleaningbuddy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.R;
import com.apep.cleaningbuddy.interfaces.OnTaskClickListener;
import com.apep.cleaningbuddy.models.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private final List<Task> data;
    private final OnTaskClickListener listener;
    private final boolean showCheckboxes;

    public TaskAdapter(List<Task> data, OnTaskClickListener listener, boolean showCheckboxes) {
        this.data = data;
        this.listener = listener;
        this.showCheckboxes = showCheckboxes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view, showCheckboxes);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = data.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskIdTf;
        private final TextView taskNameTf;
        private final TextView taskAssignedTf;
        private final CheckBox taskCompleteCb;

        public ViewHolder(@NonNull View itemView, boolean showCheckboxes) {
            super(itemView);
            taskIdTf = itemView.findViewById(R.id.task_id_tf);
            taskNameTf = itemView.findViewById(R.id.task_name_tf);
            taskAssignedTf = itemView.findViewById(R.id.task_assigned_tf);
            taskCompleteCb = itemView.findViewById(R.id.task_complete_cb);

            if (showCheckboxes) {
                taskCompleteCb.setVisibility(View.VISIBLE);
                taskAssignedTf.setVisibility(View.GONE);
            } else {
                taskCompleteCb.setVisibility(View.GONE);
                taskAssignedTf.setVisibility(View.VISIBLE);
            }
        }

        public void bind(Task task, OnTaskClickListener listener) {
            String id = "T-" + task.getId().toString();
            taskIdTf.setText(id);
            taskNameTf.setText(task.getName());
            taskAssignedTf.setText((task.getUser() == null ? "Unassigned" : task.getUser().getUsername()));
            taskCompleteCb.setChecked(task.isCompleted());

            itemView.setOnClickListener(v -> listener.onTaskClick(task));

            taskCompleteCb.setOnCheckedChangeListener((buttonView, isChecked) -> {
                task.setCompleted(isChecked);
                Task.updateTask(itemView.getContext(), task);
            });
        }
    }
}
