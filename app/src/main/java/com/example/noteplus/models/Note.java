package com.example.noteplus.models;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
//Модель данных Note
@Entity
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String header;
    private String body;

    public Note(String header, String body) {
        this.header = header;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }


}
