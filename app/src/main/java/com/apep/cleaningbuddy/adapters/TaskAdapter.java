package com.apep.cleaningbuddy.adapters;

import static android.provider.Settings.System.getString;

import android.content.Context;
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
    private final List<Task> tasks;
    private final OnTaskClickListener listener;
    private final Context context;

    public TaskAdapter(List<Task> tasks, Context context, OnTaskClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task, listener);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskIdTf;
        private final TextView taskNameTf;
        private final TextView taskAssignedTf;
        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            taskIdTf = itemView.findViewById(R.id.task_id_tf);
            taskNameTf = itemView.findViewById(R.id.task_name_tf);
            taskAssignedTf = itemView.findViewById(R.id.task_assigned_tf);
            this.context = context;
        }

        public void bind(Task task, OnTaskClickListener listener) {
            String id = "T-" + task.getId().toString();
            taskIdTf.setText(id);
            taskNameTf.setText(task.getName());
            taskAssignedTf.setText((task.getUser() == null ? context.getString(R.string.unassigned) : task.getUser().getUsername()));

            itemView.setOnClickListener(v -> listener.onTaskClick(task));
        }
    }
}
