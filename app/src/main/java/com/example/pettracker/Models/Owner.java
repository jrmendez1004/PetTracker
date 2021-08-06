package com.example.pettracker.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze = Owner.class)
@ParseClassName("Owner")
public class Owner extends ParseObject {
    public static final String KEY_MORNING_AVAILABILITY = "morningAvailability";
    public static final String KEY_AFTERNOON_AVAILABILITY = "afternoonAvailability";
    public static final String KEY_EVENING_AVAILABILITY = "eveningAvailability";
    public static final String KEY_NIGHT_AVAILABILITY = "nightAvailability";
    public static final String KEY_OWNER_NAME = "ownerName";
    public static final String KEY_HOUSEHOLD_ID = "householdID";
    public static final String KEY_COLOR = "color";

    public Owner() {
    }

    public String getOwnerName() {
        return getString(KEY_OWNER_NAME);
    }

    public ParseUser getHouseHoldId() {
        return getParseUser(KEY_HOUSEHOLD_ID);
    }

    public String getColor() {
        return getString(KEY_COLOR);
    }

    public void setOwnerName(String ownerName) {
        put(KEY_OWNER_NAME, ownerName);
    }

    public void setHouseholdID(ParseUser householdID) {
        put(KEY_HOUSEHOLD_ID, householdID);
    }

    public void setColor(String color) {
        put(KEY_COLOR, color);
    }

    public void setMorning(boolean checked) {
        put(KEY_MORNING_AVAILABILITY, checked);
    }

    public void setAfternoon(boolean checked) {
        put(KEY_AFTERNOON_AVAILABILITY, checked);
    }

    public void setEvening(boolean checked) {
        put(KEY_EVENING_AVAILABILITY, checked);
    }

    public void setKeyNight(boolean checked) {
        put(KEY_NIGHT_AVAILABILITY, checked);
    }

    public boolean getMorning() {
        return getBoolean(KEY_MORNING_AVAILABILITY);
    }

    public boolean getAfternoon() {
        return getBoolean(KEY_AFTERNOON_AVAILABILITY);
    }

    public boolean getEvening() {
        return getBoolean(KEY_EVENING_AVAILABILITY);
    }

    public boolean getNight(){
        return getBoolean(KEY_NIGHT_AVAILABILITY);
    }

}
