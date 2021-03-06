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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class AllTodoViewModel extends AndroidViewModel {

    private TodoDao todoDao;
    private MutableLiveData<Todo> todoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Todo>> todoListMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Check>> checkListMutableLiveData = new MutableLiveData<>();

    @SuppressLint("CheckResult")
    public AllTodoViewModel(@NonNull @NotNull Application application, SavedStateHandle state) {
        super(application);
        //Реализация БД и получение всеъ элементов to do с использованием RxJava2
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
        todoMutableLiveData = state.getLiveData("Default");
    }


    public LiveData<List<Check>> getCheckListLiveData() {
        return checkListMutableLiveData;
    }
    //Создание элемента
    public void createTodo(String name) {
        todoDao.addTodo(new Todo(name, checkListMutableLiveData.getValue()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
    //Обновление элемента
    public void updateTodo(Todo todo) {
        todo.setCheckList(checkListMutableLiveData.getValue());
        todoDao.updateTodo(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public LiveData<List<Todo>> getTodoListLiveData() {
        return todoListMutableLiveData;
    }


    public void setTodoMutableLiveData(Todo todo) {
        todoMutableLiveData.setValue(todo);
    }
    //добавление Check элемента
    public void addToCheckListMutableLiveData(Check check) {
        List<Check> checkList = checkListMutableLiveData.getValue();
        if (checkList != null) {
            checkList.add(check);
            checkListMutableLiveData.setValue(checkList);
        } else {
            List<Check> checks = new ArrayList<Check>();
            checks.add(check);
            checkListMutableLiveData.setValue(checks);

        }
        ;
    }

    public void setCurrentCheckListMutableLiveData(List<Check> checkList) {
        checkListMutableLiveData.setValue(checkList);
    }


    //удаление to do
    public void deleteTodo(Todo todo) {
        todoDao.deleteTodo(todo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }





}
