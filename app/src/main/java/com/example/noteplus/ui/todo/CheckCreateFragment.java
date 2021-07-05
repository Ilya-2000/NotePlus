package com.example.noteplus.ui.todo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.noteplus.R;
import com.example.noteplus.ui.all_todo.AllTodoViewModel;

import org.jetbrains.annotations.NotNull;

public class CheckCreateFragment extends DialogFragment {
    AllTodoViewModel viewModel;

    @NonNull
    @NotNull
    @Override
    public Dialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(AllTodoViewModel.class);
        View view = inflater.inflate(R.layout.create_check_dialog, container, false);
        EditText checkName = view.findViewById(R.id.check_name_text);
        Button cancelBtn = view.findViewById(R.id.cancel_check_btn);
        Button saveBtn = view.findViewById(R.id.save_check_btn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;
    }
}
