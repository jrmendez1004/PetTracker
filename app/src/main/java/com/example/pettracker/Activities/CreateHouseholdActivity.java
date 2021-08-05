package com.example.pettracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pettracker.Models.Owner;
import com.example.pettracker.R;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class CreateHouseholdActivity extends AppCompatActivity {
    private Button btnConfirm;
    private EditText etNumOwners;
    private String[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_household);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        etNumOwners = (EditText) findViewById(R.id.etNumOwners);
        colors = getResources().getStringArray(R.array.calColors);

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

        if(etNumOwners.getText().toString().equals("")) {
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
            ownerNames.add(editText); //Save edit texts for later use
            linearLayout.addView(editText);
        }
        //Add button after EditText's are added
        Button button = new Button(this);
        button.setText("Make household!");
        linearLayout.addView(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser currentUser = ParseUser.getCurrentUser(); //current user = household ID
                int j = 0; //index to use for colors
                for(int i = 0; i < ownerNames.size(); i++, j++) {
                    if(j == colors.length)
                        j = 0;

                    if(ownerNames.get(i).getText().toString().equals("")) { //Make sure every text field has a name
                        Toast.makeText(CreateHouseholdActivity.this, "Enter a name for every owner", Toast.LENGTH_SHORT).show();
                        removeOwners(currentUser);
                        return;
                    }
                    saveOwner(ownerNames.get(i).getText().toString(), currentUser, colors[j]);
                }
                //Moves along in Household creation
                finish();
                Intent intent = new Intent(CreateHouseholdActivity.this, AddDogActivity.class);
                startActivity(intent);
            }
        });
    }

    private void removeOwners(ParseUser householdID) {
        ParseQuery<Owner> query = ParseQuery.getQuery(Owner.class);
        query.whereEqualTo(Owner.KEY_HOUSEHOLD_ID, householdID);
        query.findInBackground(new FindCallback<Owner>() {
            @Override
            public void done(List<Owner> owners, ParseException e) {
                if(e != null)
                    return;
                for(Owner owner : owners) {
                    owner.deleteInBackground(new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null)
                                Toast.makeText(CreateHouseholdActivity.this, "error while deleting", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    private void saveOwner(String ownerName, ParseUser householdID, String color) {
        Owner owner = new Owner();
        owner.setOwnerName(ownerName);
        owner.setHouseholdID(householdID);
        owner.setColor(color);
        owner.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null)
                    Toast.makeText(CreateHouseholdActivity.this, "error while saving", Toast.LENGTH_SHORT).show();
            }
        });
    }
}