package com.example.noteplus.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.List;

@Entity
public class Todo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String header;
    private List<Check> checkList;

    public Todo(String header, List<Check> checkList) {
        this.header = header;
        this.checkList = checkList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @TypeConverters(CheckConverter.class)
    public List<Check> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<Check> checkList) {
        this.checkList = checkList;
    }

}
