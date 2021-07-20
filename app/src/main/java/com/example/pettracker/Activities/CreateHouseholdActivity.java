package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pettracker.Models.Owners.Owner;
import com.example.pettracker.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class CreateHouseholdActivity extends AppCompatActivity {
    Button btnConfirm;
    EditText etNumOwners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_household);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        etNumOwners = (EditText) findViewById(R.id.etNumOwners);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listOwners();
            }
        });
    }

    //Creates Edit Text fields when the user has confirmed the number of Owners
    private void listOwners() {
        if(etNumOwners.getText().toString() == "0") {
            Toast.makeText(CreateHouseholdActivity.this, "There can't be 0 owners", Toast.LENGTH_LONG).show();
            return;
        }

        if(etNumOwners.getText().toString() == "null") {
            Toast.makeText(CreateHouseholdActivity.this, "You must enter a number", Toast.LENGTH_SHORT).show();
        }

        //Make a new LinearLayout to append EditText fields to
        int numOwners = Integer.parseInt(etNumOwners.getText().toString());
        LinearLayout linearLayout = new LinearLayout(this);
        setContentView(linearLayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        List<EditText> ownerNames = new ArrayList<>();
        for( int i = 0; i < numOwners; i++ )
        {
            EditText editText = new EditText(this);
            editText.setHint("Owner Name");
            ownerNames.add(editText); //Save edit texts for later
            linearLayout.addView(editText);
        }
        Button button = new Button(this);
        button.setText("Make household!");
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < ownerNames.size(); i++) {
                    if(ownerNames.get(i).getText().toString().equals("")) { //Make sure every text field has a name
                        Toast.makeText(CreateHouseholdActivity.this, "Enter a name for every owner", Toast.LENGTH_SHORT).show();
                        //owners.clear(); needs to clear all owners that were just put in, owners with the household id
                        return;
                    }

                    //need to add owners with the household id to the database
                }

                //Moves along in Household creation
                finish();
                Intent intent = new Intent(CreateHouseholdActivity.this, AddDogActivity.class);
                startActivity(intent);
            }
        });
    }
}