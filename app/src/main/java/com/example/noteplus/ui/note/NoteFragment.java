package com.example.noteplus.ui.note;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.noteplus.R;
import com.example.noteplus.databinding.NoteFragmentBinding;
import com.example.noteplus.models.Note;
import com.example.noteplus.ui.all_notes.AllNotesViewModel;

import org.jetbrains.annotations.NotNull;

public class NoteFragment extends Fragment {

    private AllNotesViewModel mViewModel;
    private NoteFragmentBinding binding;
    private Note note;

    public static NoteFragment newInstance() {
        return new NoteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = NoteFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mViewModel = new ViewModelProvider(this).get(AllNotesViewModel.class);
        Bundle bundle = getArguments();
        if (bundle != null && (Note) bundle.getSerializable("note") != null) {
            note = (Note) bundle.getSerializable("note");
            binding.titleNoteText.setText(note.getHeader());
            binding.bodyNoteText.setText(note.getBody());
        }

        binding.createNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (note != null) {
                    Log.d("check", binding.titleNoteText.getText().toString());
                    note.setHeader(binding.titleNoteText.getText().toString());
                    note.setBody(binding.bodyNoteText.getText().toString());
                    mViewModel.updateNote(note);
                } else  {
                    mViewModel.createNote(binding.titleNoteText.getText().toString(), binding.bodyNoteText.getText().toString());
                }

            }
        });

        binding.deleteNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (note != null) {
                    binding.contentNoteLayout.setVisibility(View.GONE);
                    binding.loadNoteProgress.setVisibility(View.VISIBLE);
                    mViewModel.deleteNote(note);
                    requireActivity().onBackPressed();

                } else {
                    requireActivity().onBackPressed();
                }
            }
        });


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


}