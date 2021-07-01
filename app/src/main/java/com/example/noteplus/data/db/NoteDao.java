package com.example.noteplus.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteplus.models.Note;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface NoteDao {
    @Query("SELECT * from Note")
    Flowable<List<Note>> getAllNotes();

    @Insert(onConflict = REPLACE)
    Completable addNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    Completable updateNote(Note note);

}
