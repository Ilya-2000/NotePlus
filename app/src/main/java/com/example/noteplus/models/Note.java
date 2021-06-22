package com.example.noteplus.models;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {
    @PrimaryKey
    private int id;
    private String header;
    private String body;

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
