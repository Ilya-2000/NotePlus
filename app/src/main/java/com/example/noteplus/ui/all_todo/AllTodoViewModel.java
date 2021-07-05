package com.example.noteplus.ui.all_todo;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.noteplus.data.db.NoteRoomDb;
import com.example.noteplus.data.db.TodoDao;
import com.example.noteplus.data.db.TodoRoomDb;
import com.example.noteplus.models.Check;
import com.example.noteplus.models.Note;
import com.example.noteplus.models.Todo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class AllTodoViewModel extends AndroidViewModel {
    private final SavedStateHandle state;
    private TodoDao todoDao;
    private MutableLiveData<Todo> todoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Todo>> todoListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Check> checkMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Check>> checkListMutableLiveData = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public AllTodoViewModel(@NonNull @NotNull Application application, SavedStateHandle state) {
        super(application);
        this.state = state;
        TodoRoomDb todoRoomDb = TodoRoomDb.getDatabase(application);
        todoDao = todoRoomDb.todoDao();
        todoDao.getAllTodo()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(todoList -> todoListMutableLiveData.setValue(todoList));
    }

    public void createTodo() {

    }
    void createCheck(Check check) {

    }


}
