package com.example.noteplus.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteplus.databinding.TodoCheckBinding;
import com.example.noteplus.databinding.TodoLayoutBinding;
import com.example.noteplus.models.Check;
import com.example.noteplus.models.Todo;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//Адаптер Check элементов
public class CheckRvAdapter extends RecyclerView.Adapter<CheckRvAdapter.ViewHolder>{
    public interface OnItemClickListener {
        void onItemClick(Check check);
        void onItemChecked(Check check, int position, boolean isChecked);
    }

    private final LayoutInflater inflater;
    private List<Check> checkList;
    private final CheckRvAdapter.OnItemClickListener listener;

    public CheckRvAdapter(Context context, List<Check> checkList, CheckRvAdapter.OnItemClickListener listener) {
        this.inflater = LayoutInflater.from(context);
        this.checkList = checkList;
        this.listener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        TodoCheckBinding binding = TodoCheckBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        if (checkList != null) {
            holder.binding.setCheck(checkList.get(position));
            if (checkList != null) {
                holder.binding.deleteTodoCheck.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        listener.onItemClick(checkList.get(position));
                        notifyDataSetChanged();
                    }
                });

                holder.binding.todoCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        listener.onItemChecked(checkList.get(position), position, b);


                    }
                });

            }
        }
    }

    @Override
    public int getItemCount() {
        if (checkList != null) {
            return checkList.size();
        } else {
            return 0;
        }

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TodoCheckBinding binding;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);

        }
    }
}