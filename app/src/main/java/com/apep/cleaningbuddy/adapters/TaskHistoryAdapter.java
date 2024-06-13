package com.apep.cleaningbuddy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apep.cleaningbuddy.R;
import com.apep.cleaningbuddy.models.CompletedTask;
import com.apep.cleaningbuddy.models.User;

import java.text.SimpleDateFormat;

import java.util.List;

public class TaskHistoryAdapter extends RecyclerView.Adapter<TaskHistoryAdapter.ViewHolder> {
    private final List<CompletedTask> completedTasks;
    private final Context context;

    public TaskHistoryAdapter(List<CompletedTask> completedTasks, Context context) {
        this.completedTasks = completedTasks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.history_row, parent, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CompletedTask completedTask = completedTasks.get(position);
        holder.bind(completedTask);
    }

    @Override
    public int getItemCount() {
        return completedTasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView taskDateTf;
        private final TextView taskUserTf;
        private final Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            taskDateTf = itemView.findViewById(R.id.history_date_tf);
            taskUserTf = itemView.findViewById(R.id.history_user_tf);
            this.context = context;
        }

        public void bind(CompletedTask completedTask) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            taskDateTf.setText(dateFormat.format(completedTask.getCompletionDate()));
            User user = completedTask.getUser();
            if (user != null) {
                taskUserTf.setText(user.getUsername());
            } else {
                taskUserTf.setText(context.getString(R.string.unknown));
            }
        }
    }
}
