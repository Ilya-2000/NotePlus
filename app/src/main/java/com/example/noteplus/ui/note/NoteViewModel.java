package com.example.noteplus.ui.note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.noteplus.data.db.NoteDao;
import com.example.noteplus.data.db.NoteRoomDb;
import com.example.noteplus.models.Note;

import org.jetbrains.annotations.NotNull;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NoteViewModel extends AndroidViewModel {
    private NoteDao noteDao;

    public NoteViewModel(@NonNull @NotNull Application application) {
        super(application);
        NoteRoomDb noteDb = NoteRoomDb.getDatabase(application);
        noteDao = noteDb.noteDao();
    }

    void createNote(String title, String body) {
        noteDao.addNote(new Note(title, body))
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
    }
}