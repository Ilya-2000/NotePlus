package com.example.noteplus.data.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.noteplus.models.Todo;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

import static androidx.room.OnConflictStrategy.REPLACE;
//Интерфейс БД To do
@Dao
public interface TodoDao {
    //Получение всех элементов
    @Query("SELECT * from Todo")
    Flowable<List<Todo>> getAllTodo();
    //Добавление нового элемента
    @Insert(onConflict = REPLACE)
    Completable addTodo(Todo todo);
    //Удаление элемента
    @Delete
    Completable deleteTodo(Todo todo);
    //Обновление элемента
    @Update
    Completable updateTodo(Todo todo);

}
