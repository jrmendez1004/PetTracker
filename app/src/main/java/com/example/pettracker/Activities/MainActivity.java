package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;
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

import com.bumptech.glide.Glide;
import com.example.pettracker.Adapters.DogsAdapter;
import com.example.pettracker.Models.Owners.Owner;
import com.example.pettracker.Models.Owners.OwnerInfoActivity;
import com.example.pettracker.Models.Pets.Pet;
import com.example.pettracker.Models.Pets.PetInfoActivity;
import com.example.pettracker.Models.Task;
import com.example.pettracker.R;
import com.parse.ParseObject;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvCal;
    RecyclerView rvDogList;
    Button tempUser;
    Button tempDog;
    Button switchCal;
    TextView calViewType;
    ImageView ivAddTask;
    DogsAdapter adapter;
    List<Pet> pets;

    Owner tempOwner;
    Pet tempPet;
    Task tempTask;
    List<Task> tempList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pets = new ArrayList<>();
        tempOwner = new Owner("Jon", "House1");
        tempPet = new Pet("Max", "Bulldog", 4, "House1");
        tempTask = new Task("black", "owner1", "Do something");
        tempList = new ArrayList<>();
        tempList.add(tempTask);
        for(int i = 0; i < 5; i++)
            pets.add(tempPet);

        rvCal = (RecyclerView) findViewById(R.id.rvCal);
        rvDogList = (RecyclerView) findViewById(R.id.rvDogList);
        tempUser = (Button) findViewById(R.id.tempBtn1);
        tempDog = (Button) findViewById(R.id.tempBtn2);
        switchCal = (Button) findViewById(R.id.btnSwitchCal);
        calViewType = (TextView) findViewById(R.id.tvCalView);
        ivAddTask = (ImageView) findViewById(R.id.ivAddTask);

        ivAddTask.setImageDrawable(getResources().getDrawable(R.drawable.plus));
        calViewType.setText("This Week");
        switchCal.setText("Week");

        tempDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goDogDetails();
            }
        });

        tempUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOwnerDetails();
            }
        });

        adapter = new DogsAdapter(this, pets);
        rvDogList.setAdapter(adapter);
        rvDogList.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        adapter.notifyDataSetChanged();
    }

    private void goOwnerDetails() {
        Intent intent = new Intent(this, OwnerInfoActivity.class);
        intent.putExtra("owner", Parcels.wrap(tempOwner));
        intent.putExtra("tasks", Parcels.wrap(tempTask));
        startActivity(intent);
    }

    private void goDogDetails() {
        Intent intent = new Intent(this, PetInfoActivity.class);
        intent.putExtra("Pet",Parcels.wrap(tempPet));
        startActivity(intent);
    }
}