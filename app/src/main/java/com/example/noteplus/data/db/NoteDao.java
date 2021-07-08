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
//Интерфейс БД Note
@Dao
public interface NoteDao {
    //Получение списка Note
    @Query("SELECT * from Note")
    Flowable<List<Note>> getAllNotes();
    //Добавление в список элемента
    @Insert(onConflict = REPLACE)
    Completable addNote(Note note);
    //Удаление элемента из списка
    @Delete
    Completable deleteNote(Note note);
    //Обновление элемента из списка. У существующего элемента будут обновлены данные
    @Update
    Completable updateNote(Note note);

}
