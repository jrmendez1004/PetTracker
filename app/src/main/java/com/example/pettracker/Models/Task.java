package com.example.pettracker.Models;


import com.parse.ParseClassName;
import com.parse.ParseObject;

import org.parceler.Parcel;

@Parcel(analyze = Task.class)
@ParseClassName("Task")
public class Task extends ParseObject{
    public static final String KEY_TIME = "timeAndDate";
    public static final String KEY_DAY = "day";
    public static final String KEY_OWNER_ID = "ownerId";
    public static final String KEY_DESCRIPTION = "description";

    public Task(){
    }

    //Server Methods
    public String getTime(){
        return getString(KEY_TIME);
    }

    public void setTime(String time){
        put(KEY_TIME, time);
    }

    public String getOwnerId(){
        return getString(KEY_OWNER_ID);
    }

    public void setOwnerId(String  ownerId){
        put(KEY_OWNER_ID, ownerId);
    }

    public void setTaskDescription(String taskDescription) { put(KEY_DESCRIPTION, taskDescription); }

    public String getTaskDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public String getDay() {
        return getString(KEY_DAY);
    }

    public void setDay(String day) {
        put(KEY_DAY, day);
    }


}
