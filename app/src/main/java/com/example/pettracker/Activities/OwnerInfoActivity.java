package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.pettracker.Adapters.TasksAdapter;
import com.example.pettracker.Models.Owner;
import com.example.pettracker.Models.Task;
import com.example.pettracker.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class OwnerInfoActivity extends AppCompatActivity {

    TextView tvOwnerName;
    TextView tvTaskNum;
    RecyclerView rvTasks;
    Button btnRemove;
    Owner owner;
    TasksAdapter adapter;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_info);

        tasks = new ArrayList<>();
        adapter = new TasksAdapter(this, tasks);
        tvOwnerName = (TextView) findViewById(R.id.tvOwnerName);
        tvTaskNum = (TextView) findViewById(R.id.tvTasksNum);
        rvTasks = (RecyclerView) findViewById(R.id.rvTaskList);
        btnRemove = (Button) findViewById(R.id.btnRemoveOwner);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(adapter);

        owner = Parcels.unwrap(getIntent().getParcelableExtra("owner"));
        tasks.add(Parcels.unwrap(getIntent().getParcelableExtra("tasks")));

        tvOwnerName.setText(owner.getOwnerName());
        tvTaskNum.setText(String.format("%d Tasks Per Week", 1)); //Need to replace with array.length
        adapter.notifyDataSetChanged();
    }
}