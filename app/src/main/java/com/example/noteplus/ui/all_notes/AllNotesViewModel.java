package com.example.noteplus.ui.all_notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.noteplus.data.db.NoteDao;
import com.example.noteplus.data.db.NoteRoomDb;
import com.example.noteplus.models.Note;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class AllNotesViewModel extends AndroidViewModel {
    private NoteDao noteDao;
    private LiveData<List<Note>> noteList;
    private CompositeDisposable compositeDisposable;

    public AllNotesViewModel(@NonNull @NotNull Application application) {
        super(application);
        NoteRoomDb noteDb = NoteRoomDb.getDatabase(application);
        noteDao = noteDb.noteDao();
       /* compositeDisposable(noteDao.getAllNotes()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                {
                        setAllNotes();
                }
        ));*/


    }

   /* public LiveData<List<Note>> getAllNotes() {
        return noteList;
    }*/

    public void setAllNotes(LiveData<List<Note>> notes) {
        noteList = notes;
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }
}
