package com.example.pettracker.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.pettracker.Activities.OwnerInfoActivity;
import com.example.pettracker.Models.Owner;
import com.example.pettracker.Models.Pet;
import com.example.pettracker.R;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OwnersAdapter extends RecyclerView.Adapter<OwnersAdapter.ViewHolder>{
    Context context;
    List<Owner> owners;

    public OwnersAdapter(Context context, List<Owner> owners) {
        this.context = context;
        this.owners = owners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_owner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull OwnersAdapter.ViewHolder holder, int position) {
        Owner owner = owners.get(position);
        holder.bind(owner);
    }

    @Override
    public int getItemCount() {
        return owners.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOwnerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOwnerName = itemView.findViewById(R.id.tvOwnerName);
        }

        public void bind(Owner owner) {
            tvOwnerName.setText(owner.getOwnerName());
            if(owner.getColor().equals("red"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.red));
            else if(owner.getColor().equals("green"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.green));
            else if(owner.getColor().equals("blue"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.blue));
            else if(owner.getColor().equals("yellow"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.yellow));
            else if(owner.getColor().equals("orange"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.orange));
            else if(owner.getColor().equals("teal"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.teal_200));
            else if(owner.getColor().equals("purple"))
                tvOwnerName.setTextColor(itemView.getResources().getColor(R.color.purple_200));

            tvOwnerName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goOwnerDetails(owner);
                }
            });
        }

        private void goOwnerDetails(Owner owner) {//need to fix
            Intent intent = new Intent(context, OwnerInfoActivity.class);
            intent.putExtra("owners", Parcels.wrap(owner));
            context.startActivity(intent);
        }
    }
}
