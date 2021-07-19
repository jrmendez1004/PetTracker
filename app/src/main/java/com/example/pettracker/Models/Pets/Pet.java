package com.example.pettracker.Models.Pets;
import org.parceler.Parcel;

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
}
