package com.example.pettracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.R;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class DogTaskAdapter extends RecyclerView.Adapter<DogTaskAdapter.ViewHolder>{
    Context context;
    List<Pet> pets;

    public DogTaskAdapter(Context context, List<Pet> pets) {
        this.context = context;
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dog_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogTaskAdapter.ViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.bind(pet);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPet;
        private Spinner spinWalks;
        private Spinner spinFeeds;
        private Spinner spinCleans;
        private TextView tvPetName;
        private List<String> days;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPetName = itemView.findViewById(R.id.tvPetName);
            ivPet = itemView.findViewById(R.id.ivPet);
            spinWalks = itemView.findViewById(R.id.spinWalks);
            spinFeeds = itemView.findViewById(R.id.spinMeals);
            spinCleans = itemView.findViewById(R.id.spinCleans);

            days =new ArrayList<>();
            for(int i = 2; i < 5; i ++)
                days.add(String.valueOf(i)); //Can feed from 2 to 4 times a day
            ArrayAdapter adapterFeeds = new ArrayAdapter(context, android.R.layout.simple_spinner_item, days);
            adapterFeeds.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinFeeds.setAdapter(adapterFeeds);

            days = new ArrayList<>();
            for(int i = 1; i < 8; i++)
                days.add(String.valueOf(i));//Can clean/walk up to 7 times a week
            ArrayAdapter adapterWalks = new ArrayAdapter(context, android.R.layout.simple_spinner_item, days);
            adapterWalks.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinWalks.setAdapter(adapterWalks);

            ArrayAdapter adapterCleans = new ArrayAdapter(context, android.R.layout.simple_spinner_item, days);
            adapterCleans.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinCleans.setAdapter(adapterCleans);

        }

        public void bind(Pet pet) {
            int radius = 50;
            Glide.with(itemView).load(pet.getUrlImage()).transform(new RoundedCorners(radius)).into(ivPet);
            tvPetName.setText(pet.getPetName());

            spinFeeds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pet.setMeals(days.indexOf(parent.getItemAtPosition(position)) + 1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    pet.setMeals(2); //default number of feeds;
                }
            });

            spinCleans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pet.setCleans(days.indexOf(parent.getItemAtPosition(position)) + 1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    pet.setCleans(1); //default
                }
            });

            spinWalks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    pet.setWalks(days.indexOf(parent.getItemAtPosition(position)) + 1);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    pet.setWalks(1); //default
                }
            });
        }
    }
}
