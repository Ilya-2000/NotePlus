package com.example.noteplus.ui.all_todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.noteplus.MainActivity;
import com.example.noteplus.R;
import com.example.noteplus.databinding.FragmentAllTodoBinding;
import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.example.noteplus.ui.main.MainFragment;
import com.example.noteplus.ui.note.NoteFragment;
import com.example.noteplus.ui.todo.TodoCreateFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AllTodoFragment extends Fragment {

    private FragmentAllTodoBinding binding;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAllTodoBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoCreate();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void todoCreate() {
        FragmentContainerView fragmentContainerView = requireActivity().findViewById(R.id.fragment_container);
        fragmentContainerView.setVisibility(View.VISIBLE);
        TodoCreateFragment todoCreateFragment = new TodoCreateFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, todoCreateFragment)
                .addToBackStack(null)
                .commit();
    }
}