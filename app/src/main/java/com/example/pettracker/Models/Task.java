package com.example.pettracker.Models;


import com.parse.ParseClassName;
import org.parceler.Parcel;

import com.parse.ParseObject;

@Parcel
//@ParseClassName("Task")
public class Task {
    public static final String KEY_TIME = "timeAndDate";
    public static final String KEY_COLOR = "color";
    public static final String KEY_OWNER_ID = "ownerId";

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

    public String getTaskDescription() { return TaskDescription; }

    public String getColor() {
        return color;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public void setTaskDescription(String taskDescription) {
        TaskDescription = taskDescription;
    }

    //Server Methods
//    public String getTime(){
//        return getString(KEY_TIME);
//    }
//
//    public void setTime(String time){
//        put(KEY_TIME, time);
//    }
//
//    public String getColor(){
//        return getString(KEY_COLOR);
//    }
//
//    public void setColor(String color){
//        put(KEY_COLOR, color);
//    }
//
//    public String getOwnerId(){
//        return getString(KEY_OWNER_ID);
//    }
//
//    public void setOwnerId(String ownerId){
//        put(KEY_OWNER_ID, ownerId);
//    }
}
