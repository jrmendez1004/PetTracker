package com.example.pettracker.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.pettracker.Activities.MainActivity;
import com.example.pettracker.Models.DisplayPets;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.R;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;

public class DogBreedAdapter extends RecyclerView.Adapter<DogBreedAdapter.ViewHolder> {
    Context context;
    List<DisplayPets> pets;

    public DogBreedAdapter(Context context, List<DisplayPets> pets) {
        this.context = context;
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dog_breed, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogBreedAdapter.ViewHolder holder, int position) {
        DisplayPets pet = pets.get(position);
        holder.bind(pet);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvBreed;
        ImageView ivBreedImage;
        String name;
        String age;
        String weight;
        String gender;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvBreed = (TextView) itemView.findViewById(R.id.tvBreed);
            ivBreedImage = (ImageView) itemView.findViewById(R.id.ivBreedImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBreedClick();
                }
            });
        }

        public void bind(DisplayPets pet) {
            tvBreed.setText(pet.breed);
            int radius = 50;
            Glide.with(itemView)
                    .load(pet.urlImage)
                    .centerInside()
                    .transform(new RoundedCorners(radius))
                    .into(ivBreedImage);
        }

        private void onBreedClick() {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                DisplayPets pet = pets.get(position); //Get information from card
                showDetailsAlert(pet);
            }
        }

        //Creates Alert to enter in dog information
        private void showDetailsAlert(DisplayPets displayPets) {
            View  messageView = LayoutInflater.from(context).inflate(R.layout.message_item, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(messageView);
            final AlertDialog alertDialog = alertDialogBuilder.create();

            //setup gender select
            Spinner spinner = (Spinner) messageView.findViewById(R.id.spinGender);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.gender_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(new SpinnerActivity());

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Extract content from alert dialog
                    name = ((EditText) alertDialog.findViewById(R.id.etPetName)).getText().toString();
                    age = ((EditText) alertDialog.findViewById(R.id.etPetAge)).getText().toString();
                    weight = ((EditText) alertDialog.findViewById(R.id.etPetWeight)).getText().toString();

                    if(name.length() == 0 || age.length() == 0 || weight.length() ==0) {
                        Toast.makeText(context, "All fields must be filled", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Pet pet = new Pet();
                    pet.setUrlImage(displayPets.urlImage);
                    pet.setDescription(displayPets.description);
                    pet.setBreed(displayPets.breed);
                    pet.setAge(Integer.parseInt(age));
                    pet.setWeight(Integer.parseInt(weight));
                    pet.setPetName(name);
                    pet.setGender(gender);
                    pet.setHouseholdId(ParseUser.getCurrentUser());
                    pet.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e != null) {
                                Toast.makeText(context, "error while saving", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Toast.makeText(context,"Pet added!", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.dismiss();
                    showNextAlert();
                }
            });

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });

            alertDialog.show();
        }

        //Creates option to add another dog to household
        private void showNextAlert() {
            View  messageView = LayoutInflater.from(context).inflate(R.layout.message_next, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(messageView);
            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Yes, add another", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "No, move on", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Intent intent = new Intent(context, MainActivity.class);
                    ((Activity) context).finish();
                    context.startActivity(intent);
                }
            });

            alertDialog.show();
        }

        //Handles spinner selection
        public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                //gender = parent.getItemAtPosition(pos).toString();
                if(parent.getItemAtPosition(pos).equals("Male"))
                    gender = "Male";
                else
                    gender = "Female";
            }

            public void onNothingSelected(AdapterView<?> parent) {
                gender = "N/A";
            }
        }
    }
}
