package com.example.noteplus.data.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.noteplus.models.Note;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteRoomDb extends RoomDatabase {
    private static NoteRoomDb mInstance;

    public static NoteRoomDb getDatabase(Context context) {

        if (mInstance == null)
            mInstance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteRoomDb.class, "notes_db")
                    .build();

        return mInstance;
    }

    public static void closeDatabase() {
        mInstance = null;
    }

    public abstract NoteDao noteDao();
}
