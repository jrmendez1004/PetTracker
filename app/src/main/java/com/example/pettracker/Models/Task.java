package com.example.pettracker.Models;


import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import org.parceler.Parcel;

@Parcel(analyze = Task.class)
@ParseClassName("Task")
public class Task extends ParseObject{
    public static final String KEY_TIME = "timeAndDate";
    public static final String KEY_COLOR = "color";
    public static final String KEY_OWNER_ID = "ownerId";
    public static final String KEY_DESCRIPTION = "description";

    public Task(){
    }

    public Task(String color, String ownerId, String taskDescription) {
        this.color = color;
        this.ownerId = ownerId;
        TaskDescription = taskDescription;
    }

    String color;
    String ownerId;
    String TaskDescription;

    //Server Methods
    public String getTime(){
        return getString(KEY_TIME);
    }

    public void setTime(String time){
        put(KEY_TIME, time);
    }

    public String getColor(){
        return getString(KEY_COLOR);
    }

    public void setColor(String color){
        put(KEY_COLOR, color);
    }

    public String getOwnerId(){
        return getString(KEY_OWNER_ID);
    }

    public void setOwnerId(String ownerId){
        put(KEY_OWNER_ID, ownerId);
    }

    public void setTaskDescription(String taskDescription) {
        put(KEY_DESCRIPTION, taskDescription);
    }

    public String getTaskDescription() {
        return getString(KEY_DESCRIPTION);
    }
}
