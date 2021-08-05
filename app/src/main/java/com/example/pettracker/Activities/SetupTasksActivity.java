package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pettracker.Adapters.DogTaskAdapter;
import com.example.pettracker.Adapters.OwnerTaskAdapter;
import com.example.pettracker.Models.Owner;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.Models.Task;
import com.example.pettracker.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class SetupTasksActivity extends AppCompatActivity {
    private String[] days;
    private List<Owner> owners;
    private List<Pet> pets;
    private List<Task> generatedTasks;
    private RecyclerView rvDogTasks;
    private RecyclerView rvOwnerTasks;
    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_tasks);

        days = getResources().getStringArray(R.array.days);
        owners = Parcels.unwrap(getIntent().getParcelableExtra("owners"));
        pets = Parcels.unwrap(getIntent().getParcelableExtra("pets"));

        rvDogTasks = (RecyclerView) findViewById(R.id.rvDogTasks);
        rvOwnerTasks = (RecyclerView) findViewById(R.id.rvOwnerTasks);
        btnFinish = (Button) findViewById(R.id.btnFinish);

        DogTaskAdapter dogAdapter = new DogTaskAdapter(this, pets);
        rvDogTasks.setAdapter(dogAdapter);
        rvDogTasks.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        dogAdapter.notifyDataSetChanged();

        OwnerTaskAdapter ownerAdapter = new OwnerTaskAdapter(this, owners);
        rvOwnerTasks.setAdapter(ownerAdapter);
        rvOwnerTasks.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        ownerAdapter.notifyDataSetChanged();

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Pet pet : pets) {
                    pet.saveInBackground();
                }
            }
        });
    }

    private void setupFeeding() {
        for(String day: days) {
            //first feeding
            Task task1 = new Task();
            task1.setOwnerId(owners.get(0).getObjectId());
            task1.setTaskDescription("Feed the dog");
            task1.setDay(day);
            task1.setTime("8am");

            //second feeding
            Task task2 = new Task();
            task2.setOwnerId(owners.get(0).getObjectId());
            task2.setTaskDescription("Feed the dog");
            task2.setDay(day);
            task2.setTaskDescription("7pm");
            owners.add(owners.remove(0));
        }
    }
}