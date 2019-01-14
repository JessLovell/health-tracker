package com.example.healthtracker;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Exercise {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public String title;
    public String quantity;
    public String description;
    public String timestamp;

    public Exercise(){
    }

    public Exercise(String title, String quantity, String description, String timestamp){
        this.title = title;
        this.quantity = quantity;
        this.description = description;
        this.timestamp = timestamp;
    }
}
