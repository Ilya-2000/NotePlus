package com.example.noteplus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteplus.databinding.TodoLayoutBinding;
import com.example.noteplus.models.Todo;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TodoRvAdapter extends RecyclerView.Adapter<TodoRvAdapter.ViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(Todo todo);
    }

    private final LayoutInflater inflater;
    private List<Todo> todoList;
    private final TodoRvAdapter.OnItemClickListener listener;

    public TodoRvAdapter(Context context, List<Todo> todoList, TodoRvAdapter.OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.todoList = todoList;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        TodoLayoutBinding binding = TodoLayoutBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (todoList != null) {
            holder.binding.setTodo(todoList.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(todoList.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TodoLayoutBinding binding;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
             binding = DataBindingUtil.bind(itemView);
        }
    }
}

