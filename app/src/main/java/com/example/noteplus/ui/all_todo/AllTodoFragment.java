package com.example.noteplus.ui.all_todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.noteplus.MainActivity;
import com.example.noteplus.R;
import com.example.noteplus.databinding.FragmentAllTodoBinding;
import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.example.noteplus.ui.main.MainFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllTodoFragment extends Fragment {

    private FragmentAllTodoBinding binding;
    private NavController navController;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container);
        binding = FragmentAllTodoBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void todoCreate() {

    }
}