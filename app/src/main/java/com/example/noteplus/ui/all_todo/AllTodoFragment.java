package com.example.noteplus.ui.all_todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.noteplus.MainActivity;
import com.example.noteplus.R;
import com.example.noteplus.databinding.FragmentAllTodoBinding;
import com.example.noteplus.interfaces.FabInterface;
import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.example.noteplus.ui.main.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllTodoFragment extends Fragment implements FabInterface {

    private FragmentAllTodoBinding binding;
    private FloatingActionButton floatingActionButton;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAllTodoBinding.inflate(inflater, container, false);
        floatingActionButton = requireActivity().findViewById(R.id.fab);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainFragment mainFragment = ((MainFragment) AllTodoFragment.this.getParentFragment());
        if (mainFragment != null) {
            mainFragment.setListener(this);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void noteCreate() {
        //do nothing
    }

    @Override
    public void todoCreate() {

    }
}