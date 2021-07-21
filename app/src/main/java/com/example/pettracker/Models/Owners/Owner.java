package com.example.pettracker.Models.Owners;
import com.example.pettracker.Models.Task;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel(analyze = Owner.class)
@ParseClassName("Owner")
public class Owner extends ParseObject {
    public Owner() {
    }

    public static final String KEY_OWNER_NAME = "ownerName";
    public static final String KEY_HOUSEHOLD_ID = "householdID";

    public String getOwnerName() {
        return getString(KEY_OWNER_NAME);
    }

    public ParseUser getHouseHoldId() {
        return getParseUser(KEY_HOUSEHOLD_ID);
    }

    public void setOwnerName(String ownerName) {
        put(KEY_OWNER_NAME, ownerName);
    }

    public void setHouseholdID(ParseUser householdID) {
        put(KEY_HOUSEHOLD_ID, householdID);
    }

    //need to get all task of this owner
    public List<Task> getTasks(){
        List<Task> tasks = new ArrayList<>();
        return tasks;
    }
}
