package com.example.noteplus.ui.note;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteplus.R;
import com.example.noteplus.databinding.NoteFragmentBinding;

public class NoteFragment extends Fragment {

    private NoteViewModel mViewModel;
    private NoteFragmentBinding binding;

    private EditText titleText;
    private EditText bodyText;
    private Button noteCreateButton;

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NoteFragmentBinding.inflate(inflater, container, false);
        titleText = binding.titleNoteText;
        bodyText = binding.bodyNoteText;
        noteCreateButton = binding.createNoteBtn;
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        noteCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.createNote(titleText.getText().toString(), bodyText.getText().toString());
            }
        });

    }

}