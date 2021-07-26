package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pettracker.Adapters.CalendarAdapter;
import com.example.pettracker.Adapters.DogsAdapter;
import com.example.pettracker.Models.Owners.Owner;
import com.example.pettracker.Models.Owners.OwnerInfoActivity;
import com.example.pettracker.Models.Pets.Pet;
import com.example.pettracker.Models.Pets.PetInfoActivity;
import com.example.pettracker.Models.Task;
import com.example.pettracker.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvCal;
    RecyclerView rvDogList;
    Button tempUser;
    Button switchCal;
    TextView calViewType;
    ImageView ivAddTask;
    DogsAdapter dogsAdapter;
    CalendarAdapter calendarAdapter;
    List<Pet> pets; //Need to get list of pets from database
    List<Task> tasks;
    List<Owner> owners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pets = new ArrayList<>();
        tasks = new ArrayList<>();
        owners = new ArrayList<>();
        rvCal = (RecyclerView) findViewById(R.id.rvCal);
        rvDogList = (RecyclerView) findViewById(R.id.rvDogList);
        tempUser = (Button) findViewById(R.id.tempBtn1);
        switchCal = (Button) findViewById(R.id.btnSwitchCal);
        calViewType = (TextView) findViewById(R.id.tvCalView);
        ivAddTask = (ImageView) findViewById(R.id.ivAddTask);

        ivAddTask.setImageDrawable(getResources().getDrawable(R.drawable.plus));
        calViewType.setText("This Week");
        switchCal.setText("Week");

        tempUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOwnerDetails();
            }
        });

        ivAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddTask(ParseUser.getCurrentUser());
                //removeAllTasks();
            }
        });

        loadOwners(ParseUser.getCurrentUser());

        dogsAdapter = new DogsAdapter(this, pets);
        rvDogList.setAdapter(dogsAdapter);
        rvDogList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        loadPets(ParseUser.getCurrentUser());

        calendarAdapter = new CalendarAdapter(this, tasks);
        rvCal.setAdapter(calendarAdapter);
        rvCal.setLayoutManager(new GridLayoutManager(this, 7));
        loadTasks(ParseUser.getCurrentUser());

        rvCal.addItemDecoration(new DividerItemDecoration(rvCal.getContext(), DividerItemDecoration.HORIZONTAL));
        rvCal.addItemDecoration(new DividerItemDecoration(rvCal.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void loadTasks(ParseUser currentUser) {
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        for(Owner owner: owners)
            query.whereEqualTo(Task.KEY_OWNER_ID, owner.getObjectId());
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(List<Task> queryTasks, ParseException e) {
                if(e != null)
                    return;
                tasks.addAll(queryTasks);
                calendarAdapter.notifyDataSetChanged();
            }
        });
    }

    private void loadOwners(ParseUser householdID) {
        ParseQuery<Owner> query = ParseQuery.getQuery(Owner.class);
        query.whereEqualTo(Owner.KEY_HOUSEHOLD_ID, householdID);
        query.findInBackground(new FindCallback<Owner>() {
            @Override
            public void done(List<Owner> queryOwners, ParseException e) {
                if(e != null)
                    return;
                owners.addAll(queryOwners);
            }
        });
    }

    private void loadPets(ParseUser householdID) {
        ParseQuery<Pet> query = ParseQuery.getQuery(Pet.class);
        query.whereEqualTo(Pet.KEY_HOUSEHOLD_ID, householdID);
        query.findInBackground(new FindCallback<Pet>() {
            @Override
            public void done(List<Pet> queryPets, ParseException e) {
                if(e != null)
                    return;
                pets.addAll(queryPets);
                dogsAdapter.notifyDataSetChanged();
            }
        });
    }

    //Need to get owner from database
    private void goOwnerDetails() {
        Intent intent = new Intent(this, OwnerInfoActivity.class);
        startActivity(intent);
    }

    public void onAddTask(ParseUser householdID) {
        Task task = new Task();
        task.setTaskDescription("Walk the dog");
        task.setColor("blue");
        task.setTime("8am");
        task.setOwnerId(owners.get(0).getObjectId());
        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null)
                    Toast.makeText(MainActivity.this, "Error adding task", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this,"Task added", Toast.LENGTH_SHORT).show();
            }
        });
        tasks.add(task);
        calendarAdapter.notifyDataSetChanged();
    }

    //Helper functions
    public void removeAllTasks() {
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.include(Task.KEY_OWNER_ID);
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(List<Task> objects, ParseException e) {
                if(e != null)
                    return;
                for(Task task:objects)
                    task.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {

                        }
                    });
            }
        });
    }
}