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
import com.example.pettracker.R;

import org.jetbrains.annotations.NotNull;

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

    public void clear(){
        pets.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Pet> list){
        pets.addAll(list);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPetName;
        ImageView ivPetImage;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tvPetName = (TextView) itemView.findViewById(R.id.tvPetName);
            ivPetImage = (ImageView) itemView.findViewById(R.id.ivPetImage);
        }

        public void bind(Pet pet) {
            tvPetName.setText(pet.getPetName());
            int radius = 50;
            Glide.with(itemView)
                    .load(itemView.getResources().getDrawable(R.drawable.dog))
                    .centerInside()
                    .transform(new RoundedCorners(radius))
                    .into(ivPetImage);
        }
    }
}
