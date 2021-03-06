package com.example.noteplus.ui.todo;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteplus.R;
import com.example.noteplus.adapters.CheckRvAdapter;
import com.example.noteplus.databinding.TodoCreateFragmentBinding;
import com.example.noteplus.models.Check;
import com.example.noteplus.models.Note;
import com.example.noteplus.models.Todo;
import com.example.noteplus.ui.all_todo.AllTodoFragment;
import com.example.noteplus.ui.all_todo.AllTodoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//Класс фрагмента создания to do
public class TodoCreateFragment extends Fragment {

    private AllTodoViewModel mViewModel;
    private TodoCreateFragmentBinding binding;
    private Todo todo;
    private RecyclerView recyclerView;
    private CheckRvAdapter adapter;

    public static TodoCreateFragment newInstance() {
        return new TodoCreateFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = TodoCreateFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = binding.todoCheckRv;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        //Получение данных элемента из экрана списка to do
        Bundle bundle = getArguments();
        if (bundle != null && (Todo) bundle.getSerializable("todo") != null) {
            todo = (Todo) bundle.getSerializable("todo");
            binding.titleTodoText.setText(todo.getHeader());

        }
        //Создание to do
        binding.createTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTodo();
            }
        });
        //Удаление элемента
        binding.deleteTodoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (todo != null) {
                    mViewModel.deleteTodo(todo);
                }
                requireActivity().onBackPressed();

            }
        });
        //Добавление Check элемента
        binding.addCheckFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.checkCreateLayout.setVisibility(View.VISIBLE);
                binding.checkNameEditText.setText("");

            }
        });
        //Сохранение Check
        binding.saveCreateCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createCheck();
            }
        });
        //Отмена создания Check
        binding.cancelCreateCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.checkCreateLayout.setVisibility(View.GONE);
            }
        });


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AllTodoViewModel.class);
        if (todo != null) {
            mViewModel.setCurrentCheckListMutableLiveData(todo.getCheckList());
        }
        //Получение Check списка, удаление и установка галочки CheckBox
        mViewModel.getCheckListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Check>>() {
            @Override
            public void onChanged(List<Check> checks) {
                adapter = new CheckRvAdapter(requireContext(), checks, new CheckRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Check check) {
                        checks.remove(check);
                    }

                    @Override
                    public void onItemChecked(Check check, int position, boolean isChecked) {

                        todo.getCheckList().get(position).setChecked(isChecked);
                    }


                });
                recyclerView.setAdapter(adapter);
            }
        });

    }
    //Создание Check элемента
    public void createCheck() {
        Check check = new Check();
        check.setName(binding.checkNameEditText.getText().toString());
        check.setChecked(false);
        mViewModel.addToCheckListMutableLiveData(check);
        binding.checkCreateLayout.setVisibility(View.GONE);
        binding.createTodoMainLayout.setVisibility(View.VISIBLE);
    }
    //Создание или обновление to do элемента
    public void createTodo() {
        if (todo != null) {
            todo.setHeader(binding.titleTodoText.getText().toString());
            mViewModel.updateTodo(todo);
        } else {
            mViewModel.createTodo(binding.titleTodoText.getText().toString());
        }
        requireActivity().onBackPressed();
    }

}