package com.example.pettracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pettracker.Models.Task;
import com.example.pettracker.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>{
    Context context;
    List<Task> tasks;

    public CalendarAdapter(Context context, List<Task> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_calendar_task, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarAdapter.ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTaskTime;
        TextView tvTaskTitle;
        CardView cvTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTaskTime = itemView.findViewById(R.id.tvTaskTime);
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            cvTask = itemView.findViewById(R.id.cvTask);
        }

        public void bind(Task task) {
            tvTaskTitle.setText(task.getTaskDescription());
            tvTaskTime.setText(task.getTime());
            if(task.getColor().equals("red"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.red));
            else if(task.getColor().equals("green"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.green));
            else if(task.getColor().equals("blue"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.blue));
            else if(task.getColor().equals("yellow"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.yellow));
            else if(task.getColor().equals("orange"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.orange));
            else if(task.getColor().equals("teal"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.teal_200));
            else if(task.getColor().equals("purple"))
                cvTask.setCardBackgroundColor(itemView.getResources().getColor(R.color.purple_200));
        }
    }
}
