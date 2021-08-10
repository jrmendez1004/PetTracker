package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pettracker.Adapters.DogTaskAdapter;
import com.example.pettracker.Adapters.DogsAdapter;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.R;

import org.parceler.Parcels;

public class PetInfoActivity extends AppCompatActivity {

    TextView tvDogName;
    TextView tvDogAge;
    TextView tvDescription;
    ImageView ivDogImage;
    Button btnRemove;
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_info);

        tvDogAge = (TextView) findViewById(R.id.tvDogAge);
        tvDogName = (TextView) findViewById(R.id.tvDogName);
        tvDescription = (TextView) findViewById(R.id.tvDogDescription);
        ivDogImage = (ImageView) findViewById(R.id.ivDogImage);
        btnRemove = (Button) findViewById(R.id.btnRemovePet);

        pet = Parcels.unwrap(getIntent().getParcelableExtra("pet"));

        tvDogName.setText(pet.getPetName());
        tvDogAge.setText(String.format("%d years old", pet.getAge()));
        tvDescription.setText(pet.getDescription());
        Glide.with(this).load(pet.getUrlImage()).into(ivDogImage);

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletePet();
            }
        });
    }

    //started activity for result
    private void deletePet() {
        Intent intent = new Intent();
        intent.putExtra("petToDelete", Parcels.wrap(pet));
        setResult(RESULT_OK, intent);
        finish();
    }
}