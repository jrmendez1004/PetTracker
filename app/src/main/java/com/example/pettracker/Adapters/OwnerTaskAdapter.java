package com.example.pettracker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pettracker.Models.DisplayPets;
import com.example.pettracker.Models.Owner;
import com.example.pettracker.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OwnerTaskAdapter extends RecyclerView.Adapter<OwnerTaskAdapter.ViewHolder>{
    Context context;
    List<Owner> owners;

    public OwnerTaskAdapter(Context context, List<Owner> owners) {
        this.context = context;
        this.owners = owners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_owner_task, parent, false);
        return new OwnerTaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OwnerTaskAdapter.ViewHolder holder, int position) {
        Owner owner = owners.get(position);
        holder.bind(owner);
    }

    @Override
    public int getItemCount() {
        return owners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvOwnerName;
        private CheckBox cbMorning, cbAfternoon, cbEvening, cbNight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOwnerName = itemView.findViewById(R.id.tvOwnerName);
            cbMorning = itemView.findViewById(R.id.cbMorning);
            cbAfternoon = itemView.findViewById(R.id.cbAfternoon);
            cbEvening = itemView.findViewById(R.id.cbEvening);
            cbNight = itemView.findViewById(R.id.cbNight);

            cbMorning.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateAvailability();
                }
            });

            cbAfternoon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateAvailability();
                }
            });

            cbEvening.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateAvailability();
                }
            });

            cbNight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateAvailability();
                }
            });
        }

        public void bind(Owner owner) {
            tvOwnerName.setText(owner.getOwnerName());
        }

        private void updateAvailability() {
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                Owner owner = owners.get(position); //Get information from card
                owner.setMorning(cbMorning.isChecked());
                owner.setAfternoon(cbAfternoon.isChecked());
                owner.setEvening(cbEvening.isChecked());
                owner.setKeyNight(cbNight.isChecked());
            }
        }
    }
}
