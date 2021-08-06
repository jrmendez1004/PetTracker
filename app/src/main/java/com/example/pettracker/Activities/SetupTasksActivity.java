package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.pettracker.Adapters.DogTaskAdapter;
import com.example.pettracker.Adapters.OwnerTaskAdapter;
import com.example.pettracker.Models.Owner;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.Models.Task;
import com.example.pettracker.R;
import com.parse.ParseException;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class SetupTasksActivity extends AppCompatActivity {
    private String[] days;
    private List<Owner> owners;
    private List<Pet> pets;
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
                exitActivity();
            }
        });
    }

    public void exitActivity() {
        for(Pet pet : pets) {
            pet.saveInBackground();
        }

        for(Owner owner : owners) {
            owner.saveInBackground();
        }

        setupFeeding();
        setupWalks();
        setupCleans();
        finish();
    }

    private void setupFeeding() {
        List<Owner> skippedOwners = new ArrayList<>();
        for(Pet pet:pets) {
            for(String day:days) {
                String time = getEarliestAvailable(owners.get(0), 0); // 0 ==> before morning
                String tempTime = time;
                addTask(owners.get(0), "Feed " + pet.getPetName(), day, time);
                owners.add(owners.remove(0));
                for (int i = 0; i < pet.getMeals() - 1; i++) {
                    if(time.equals("9am"))
                        time = getEarliestAvailable(owners.get(0), 1);
                    else if(time.equals("1pm"))
                        time = getEarliestAvailable(owners.get(0), 2);
                    else if(time.equals("6pm"))
                        time = getEarliestAvailable(owners.get(0), 3);
                    if(!tempTime.equals(time)) {
                        addTask(owners.get(0), "Feed " + pet.getPetName(), day, time);
                        owners.add(owners.remove(0));
                        owners.addAll(0, skippedOwners);
                        skippedOwners.clear();
                    }
                    else if(owners.size() > 1){
                        skippedOwners.add(owners.remove(0));
                    }
                    tempTime = time;
                }
            }
        }
    }

    private void setupWalks() {
        List<String> previousDays = new ArrayList<>();
        for(Pet pet:pets) {
            for(int i = 0; i < pet.getWalks(); i++) {
                String time = getLatestAvailable(owners.get(0));
                int randomDay = (int) Math.floor((Math.random() * days.length));
                String day = days[randomDay];
                while(previousDays.contains(day)) {
                    randomDay++;
                    if(randomDay >= days.length)
                        randomDay = 0;
                    day = days[randomDay];
                }
                addTask(owners.get(0), "Walk " + pet.getPetName(), day, time);
                previousDays.add(day);
                owners.add(owners.remove(0));
            }
        }
    }

    private void setupCleans() {
        List<String> previousDays = new ArrayList<>();
        for(Pet pet:pets) {
            for(int i = 0; i < pet.getCleans(); i++) {
                String time = "Sometime Today";
                int randomDay = (int) Math.floor((Math.random() * days.length));
                String day = days[randomDay];
                while(previousDays.contains(day)) {
                    randomDay++;
                    if(randomDay >= days.length)
                        randomDay = 0;
                    day = days[randomDay];
                }
                addTask(owners.get(0), "Clean up after" + pet.getPetName(), day, time);
                previousDays.add(day);
                owners.add(owners.remove(0));
            }
        }
    }

    public String getEarliestAvailable(Owner owner, int after) {
        if(owner.getMorning() && after < 1)
            return "9am";
        else if(owner.getAfternoon() && after < 2)
            return "1pm";
        else if(owner.getEvening() && after < 3)
            return "6pm";
        else if(owner.getNight())
            return "10pm";
        return "9am";
    }

    public String getLatestAvailable(Owner owner) {
        if(owner.getNight())
            return "10pm";
        else if(owner.getEvening())
            return "6pm";
        else if(owner.getAfternoon())
            return "1pm";
        else if(owner.getMorning())
            return "9am";
        return "9am";
    }

    private void addTask(Owner owner, String taskTitle, String taskDay, String taskTime) {
        Task task = new Task();
        task.setTaskDescription(taskTitle);
        task.setDay(taskDay);
        task.setTime(taskTime);
        task.setOwnerId(owner.getObjectId());
        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null)
                    Toast.makeText(SetupTasksActivity.this, "Error adding task", Toast.LENGTH_SHORT).show();
                Toast.makeText(SetupTasksActivity.this, "Task added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}