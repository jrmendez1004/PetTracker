package com.example.pettracker.Models.Pets;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

        pet = Parcels.unwrap(getIntent().getParcelableExtra("Pet"));

        tvDogName.setText(pet.getPetName());
        tvDogAge.setText(String.format("%d years old", pet.getAge()));
        tvDescription.setText(getDescription());
        Glide.with(this).load(getImage()).into(ivDogImage);
    }

    //Needs to Access API to get a dog image
    private Drawable getImage() {
        return getResources().getDrawable(R.drawable.dog);
    }


    //Needs to Access API to get a description
    private String getDescription() {
        return "Temporary Description";
    }
}