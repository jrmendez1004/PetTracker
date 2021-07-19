package com.example.pettracker.Models.Owners;
import com.example.pettracker.Models.Task;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Owner {

    String ownerName;
    String houseHoldId;

    public Owner() {
    }

    public Owner(String ownerName, String houseHoldId) {
        this.ownerName = ownerName;
        this.houseHoldId = houseHoldId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getHouseHoldId() {
        return houseHoldId;
    }

    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();
        return tasks;
    }
}
