package com.example.noteplus.ui.all_notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.noteplus.data.db.NoteDao;
import com.example.noteplus.data.db.NoteRoomDb;
import com.example.noteplus.models.Note;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllNotesViewModel extends AndroidViewModel {
    private NoteDao noteDao;
    private LiveData<List<Note>> noteList;

    public AllNotesViewModel(@NonNull @NotNull Application application) {
        super(application);
        NoteRoomDb noteDb = NoteRoomDb.getDatabase(application);
        noteDao = noteDb.noteDao();
        //noteList = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return noteList;
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note);
    }
}
