package com.example.pettracker;

import android.app.Application;

import com.example.pettracker.Models.Owners.Owner;
import com.example.pettracker.Models.Pets.Pet;
import com.example.pettracker.Models.Task;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Owner.class);
        ParseObject.registerSubclass(Pet.class);
        ParseObject.registerSubclass(Task.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mp1PduCSVspyRI97GJwtSceHCXFUCBfxHbGgwQ66")
                .clientKey("lZdiwPhcuJfiYYyXATTK6nhVBmaTAiDX0DATXanB")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
