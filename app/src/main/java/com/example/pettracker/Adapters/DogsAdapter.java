package com.example.pettracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.pettracker.Models.Pets.Pet;
import com.example.pettracker.Models.Pets.PetInfoActivity;
import com.example.pettracker.R;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder>{
    Context context;
    List<Pet> pets;

    public DogsAdapter(Context context, List<Pet> pets) {
        this.context = context;
        this.pets = pets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogsAdapter.ViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.bind(pet);
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPetName;
        ImageView ivPetImage;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvPetName = (TextView) itemView.findViewById(R.id.tvPetName);
            ivPetImage = (ImageView) itemView.findViewById(R.id.ivPetImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPetClick();
                }
            });
        }

        private void onPetClick() {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                Pet pet = pets.get(position);
                goPetDetails(pet);
            }
        }

        //need to get Dog from database
        private void goPetDetails(Pet pet) {
            Intent intent = new Intent(context, PetInfoActivity.class);
            intent.putExtra("pet", Parcels.wrap(pet));
            context.startActivity(intent);
        }

        public void bind(Pet pet) {
            tvPetName.setText(pet.getPetName());
            int radius = 50;
            Glide.with(itemView)
                    .load(pet.getUrlImage())
                    .centerInside()
                    .transform(new RoundedCorners(radius))
                    .into(ivPetImage);
        }
    }
}
