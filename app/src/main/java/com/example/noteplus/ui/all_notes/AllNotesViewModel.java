package com.example.noteplus.ui.all_notes;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.noteplus.data.db.NoteDao;
import com.example.noteplus.data.db.NoteRoomDb;
import com.example.noteplus.models.Note;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class AllNotesViewModel extends AndroidViewModel {
    private final String TAG = "AllNotesViewModel";
    private NoteDao noteDao;
    private MutableLiveData<List<Note>> noteListLiveData = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public AllNotesViewModel(@NonNull @NotNull Application application) {
        super(application);
        NoteRoomDb noteDb = NoteRoomDb.getDatabase(application);
        noteDao = noteDb.noteDao();
        noteDao.getAllNotes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Note>>() {
                    @Override
                    public void accept(List<Note> notes) throws Exception {
                        Log.d(TAG, String.valueOf(notes.get(0).getId()));
                        noteListLiveData.setValue(notes);
                    }
                });


    }


    public LiveData<List<Note>> getNoteListLiveData() {
        return noteListLiveData;
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }
}
