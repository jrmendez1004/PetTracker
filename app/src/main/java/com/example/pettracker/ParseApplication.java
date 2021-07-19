package com.example.pettracker;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import bolts.Task;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("mp1PduCSVspyRI97GJwtSceHCXFUCBfxHbGgwQ66")
                .clientKey("lZdiwPhcuJfiYYyXATTK6nhVBmaTAiDX0DATXanB")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
