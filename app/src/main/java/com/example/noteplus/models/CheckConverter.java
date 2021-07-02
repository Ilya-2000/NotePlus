package com.example.noteplus.models;

import android.renderscript.Type;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CheckConverter {

    private static Gson gson = new Gson();

    @TypeConverter
    public String fromCheck(List<Check> checkList) {
        return gson.toJson(checkList, new TypeToken<List<Check>>() {}.getType());
        //return checkList.stream().collect(Collectors.joining(","));
    }

    @TypeConverter
    public List<Check> toCheckList(String data) {
        return gson.fromJson(data, new TypeToken<List<Check>>() {}.getType());
        //return Arrays.asList(data.split(","));
    }

}