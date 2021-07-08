package com.example.noteplus.ui.all_todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteplus.MainActivity;
import com.example.noteplus.R;
import com.example.noteplus.adapters.TodoRvAdapter;
import com.example.noteplus.databinding.FragmentAllTodoBinding;
import com.example.noteplus.models.Check;
import com.example.noteplus.models.Todo;
import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.example.noteplus.ui.all_notes.AllNotesViewModel;
import com.example.noteplus.ui.main.MainFragment;
import com.example.noteplus.ui.note.NoteFragment;
import com.example.noteplus.ui.todo.TodoCreateFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
//Класс фрагмента со списком to do
public class AllTodoFragment extends Fragment {

    private FragmentAllTodoBinding binding;
    private AllTodoViewModel viewModel;
    private TodoRvAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAllTodoBinding.inflate(inflater, container, false);
        recyclerView = binding.todoRv;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AllTodoViewModel.class);
        //Создание to do
        binding.fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoCreate();
            }
        });
        //Переход на элемент
        viewModel.getTodoListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todoList) {
                adapter = new TodoRvAdapter(requireContext(), todoList, new TodoRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Todo todo) {
                        if (savedInstanceState == null) {
                            viewModel.setTodoMutableLiveData(todo);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("todo", todo);
                            Fragment fragInstance;
                            fragInstance = TodoCreateFragment.newInstance();
                            fragInstance.setArguments(bundle);
                            getFragmentManager().beginTransaction()
                                    .add(R.id.fragment_container, fragInstance)
                                    .addToBackStack(null)
                                    .commit();
                        }
                    }
                });
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    //Переход на экран создания to do
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