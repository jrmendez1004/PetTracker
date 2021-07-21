package com.example.pettracker.Models.Pets;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel(analyze = Pet.class)
@ParseClassName("Pet")
public class Pet extends ParseObject {
    public static int DEFAULT_AGE = 999; //Set Age for unknown age of dog
    public static final String KEY_NAME = "petName";
    public static final String KEY_BREED = "breed";
    public static final String KEY_AGE = "age";
    public static final String KEY_HOUSEHOLD_ID = "householdID";
    public static final String KEY_IMAGE_URL = "urlImage";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_WEIGHT = "weight";

    public Pet() {
    }

    public String getPetName() {
        return getString(KEY_NAME);
    }

    public String getBreed() {
        return getString(KEY_BREED);
    }

    public int getAge() {
        return getInt(KEY_AGE);
    }

    public String getHouseHoldId() {
        return getString(KEY_HOUSEHOLD_ID);
    }

    public ParseUser getUrlImage() { return getParseUser(KEY_IMAGE_URL); }

    public int getWeight() { return getInt(KEY_WEIGHT); }


    public void setPetName(String petName) {  put(KEY_NAME, petName); }

    public void setBreed(String breed) { put(KEY_BREED, breed); }

    public void setAge(int age) { put(KEY_AGE, age); }

    public void setUrlImage(String urlImage) { put(KEY_IMAGE_URL, urlImage); }

    public void setDescription(String description) { put(KEY_DESCRIPTION, description); }

    public void setWeight(int weight) { put(KEY_WEIGHT, weight); }

    public void setHouseholdId(ParseUser householdId) { put(KEY_HOUSEHOLD_ID, householdId); }

}
