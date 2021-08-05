package com.example.pettracker.Models;
import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze = Pet.class)
@ParseClassName("Pet")
public class Pet extends ParseObject {
    private static final String KEY_MEALS_PER_WEEK = "mealsPerWeek";
    private static final String KEY_CLEANS_PER_WEEK = "cleansPerWeek";
    private static final String KEY_WALKS_PER_WEEK = "walksPerWeek";
    public static int DEFAULT_AGE = 999; //Set Age for unknown age of dog
    public static final String KEY_NAME = "petName";
    public static final String KEY_BREED = "breed";
    public static final String KEY_AGE = "age";
    public static final String KEY_HOUSEHOLD_ID = "householdID";
    public static final String KEY_IMAGE_URL = "urlImage";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_WEIGHT = "weight";
    public static final String KEY_GENDER = "gender";

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

    public ParseUser getHouseHoldId() {
        return getParseUser(KEY_HOUSEHOLD_ID);
    }

    public String getUrlImage() {
        return getString(KEY_IMAGE_URL);
    }

    public int getWeight() {
        return getInt(KEY_WEIGHT);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public String getGender() {
        return getString(KEY_GENDER);
    }

    public int getMeals() {
        return getInt(KEY_MEALS_PER_WEEK);
    }

    public int getCleans() {
        return getInt(KEY_CLEANS_PER_WEEK);
    }

    public int getWalks() {
        return getInt(KEY_WALKS_PER_WEEK);
    }


    public void setPetName(String petName) {
        put(KEY_NAME, petName);
    }

    public void setBreed(String breed) {
        put(KEY_BREED, breed);
    }

    public void setAge(int age) {
        put(KEY_AGE, age);
    }

    public void setUrlImage(String urlImage) {
        put(KEY_IMAGE_URL, urlImage);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public void setWeight(int weight) {
        put(KEY_WEIGHT, weight);
    }

    public void setHouseholdId(ParseUser householdId) {
        put(KEY_HOUSEHOLD_ID, householdId);
    }

    public void setGender(String gender) {
        put(KEY_GENDER, gender);
    }

    public void setMeals(int meals) {
        put(KEY_MEALS_PER_WEEK, meals);
    }

    public void setCleans(int cleans) {
        put(KEY_CLEANS_PER_WEEK, cleans);
    }

    public void setWalks(int walks) {
        put(KEY_WALKS_PER_WEEK, walks);
    }
}
