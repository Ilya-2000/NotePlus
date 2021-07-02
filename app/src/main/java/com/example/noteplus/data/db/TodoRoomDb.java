package com.example.noteplus.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.noteplus.models.CheckConverter;
import com.example.noteplus.models.Todo;

@Database(entities = Todo.class, version = 1, exportSchema = false)
@TypeConverters(CheckConverter.class)
public abstract class TodoRoomDb extends RoomDatabase {
    private static TodoRoomDb mInstance;

    public static TodoRoomDb getDatabase(Context context) {

        if (mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    TodoRoomDb.class, "todo_db")
                    .build();

        return mInstance;
    }

    public static void closeDatabase() {
        mInstance = null;
    }

    public abstract TodoDao todoDao();
}