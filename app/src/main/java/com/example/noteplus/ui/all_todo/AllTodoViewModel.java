package com.example.noteplus.ui.all_todo;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
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
import java.util.Objects;

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
                .subscribe(new Consumer<List<Todo>>() {
                    @Override
                    public void accept(List<Todo> todoList) throws Exception {
                        todoListMutableLiveData.setValue(todoList);
                    }
                });
    }

    void setTodo(int index) {
        todoMutableLiveData.setValue(Objects.requireNonNull(todoListMutableLiveData.getValue()).get(index));
    }

    public void createTodo() {

    }
    public void setCheckListMutableLiveData(List<Check> checkList) {
        checkListMutableLiveData.setValue(checkList);
    }

    public void setCheckMutableLiveData(Check check) {
        checkMutableLiveData.setValue(check);
        addCheckToList();
    }

    void addCheckToList() {
        List<Check> checkList = checkListMutableLiveData.getValue();
        if (checkList != null) {
            checkList.add(checkMutableLiveData.getValue());
        }
        checkListMutableLiveData.setValue(checkList);
    }

    public LiveData<List<Todo>> getTodoListLiveData() {
        return todoListMutableLiveData;
    }


}
