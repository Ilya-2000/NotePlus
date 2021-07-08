package com.example.noteplus.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
//Модель данных Check
@Entity
public class Check {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private boolean isChecked;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
