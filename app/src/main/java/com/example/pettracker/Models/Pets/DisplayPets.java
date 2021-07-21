package com.example.pettracker.Models.Pets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayPets {

    public String breed;
    public String description;
    public String urlImage;

    public DisplayPets(String breed, String description, String urlImage) {
        this.breed = breed;
        this.description = description;
        this.urlImage = urlImage;
    }

    public static List<DisplayPets> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<DisplayPets> pets = new ArrayList<>();
        for(int i = 0; i < jsonArray.length(); i++)
            pets.add(fromJson(jsonArray.getJSONObject(i)));

        return pets;
    }

    private static DisplayPets fromJson(JSONObject jsonObject) throws JSONException{
        DisplayPets pet = new DisplayPets(jsonObject.getString("name")
                ,"No description found"
                , jsonObject.getJSONObject("image").getString("url"));
        if(jsonObject.has("temperament"))
            pet.description = jsonObject.getString("temperament");

        return pet;
    }
}
