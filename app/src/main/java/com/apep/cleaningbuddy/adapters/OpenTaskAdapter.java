package com.apep.cleaningbuddy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.R;
import com.apep.cleaningbuddy.models.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OpenTaskAdapter extends RecyclerView.Adapter<OpenTaskAdapter.ViewHolder> {
    private final List<Task> tasks;
    private final List<Boolean> checkedStates;

    public OpenTaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
        this.checkedStates = new ArrayList<>(Collections.nCopies(tasks.size(), false));
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.open_task_row, parent, false);

        return new ViewHolder(view, checkedStates);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task, position);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public List<Task> getCheckedTasks() {
        List<Task> checkedTasks = new ArrayList<>();
        for (int i = 0; i < checkedStates.size(); i++) {
            if (checkedStates.get(i)) {
                checkedTasks.add(tasks.get(i));
            }
        }
        return checkedTasks;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final List<Boolean> checkedStates;
        private final CheckBox taskCompletedTf;
        private final TextView taskIdTf;
        private final TextView taskNameTf;
        public ViewHolder(@NonNull View itemView, List<Boolean> checkedStates) {
            super(itemView);
            this.checkedStates = checkedStates;
            taskIdTf = itemView.findViewById(R.id.task_id_tf);
            taskNameTf = itemView.findViewById(R.id.task_name_tf);
            taskCompletedTf = itemView.findViewById(R.id.task_confirm_tf);
        }

        public void bind(Task task, int position) {
            String id = "T-" + task.getId().toString();
            taskIdTf.setText(id);
            taskNameTf.setText(task.getName());

            taskCompletedTf.setOnCheckedChangeListener((buttonView, isChecked) -> {
                checkedStates.set(position, isChecked);
            });
        }
    }
}
