package com.example.pettracker.Models.Pets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Pet {
    public static int DEFAULT_AGE = 999; //Set Age for unknown age of dog

    String petName;
    String breed;
    int age = DEFAULT_AGE;
    String houseHoldId;
    String urlImage;
    String description;

    public Pet() {
    }

    public Pet(String petName, String breed, int age, String houseHoldId) {
        this.petName = petName;
        this.breed = breed;
        this.age = age;
        this.houseHoldId = houseHoldId;
    }

    public Pet(String breed, String urlImage, String description) {
        this.breed = breed;
        this.urlImage = urlImage;
        this.description = description;
    }

    public String getPetName() {
        return petName;
    }

    public String getBreed() {
        return breed;
    }

    public int getAge() {
        return age;
    }

    public String getHouseHoldId() {
        return houseHoldId;
    }

    public String getUrlImage() { return urlImage; }

    public void setPetName(String petName) { this.petName = petName; }

    public void setBreed(String breed) { this.breed = breed; }

    public void setAge(int age) { this.age = age; }

    public void setUrlImage(String urlImage) { this.urlImage = urlImage; }

    public void setDescription(String description) { this.description = description; }

    public static List<Pet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Pet> pets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++)
            pets.add(fromJson(jsonArray.getJSONObject(i)));

        return pets;
    }

    private static Pet fromJson(JSONObject jsonObject) throws JSONException{
        Pet pet = new Pet();
        pet.setBreed(jsonObject.getString("name"));
        pet.setDescription(jsonObject.getString("temperament"));
        pet.setUrlImage(jsonObject.getJSONObject("image").getString("url"));

        return pet;
    }

}
