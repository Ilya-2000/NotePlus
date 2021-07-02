package com.example.noteplus.ui.all_notes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.noteplus.MainActivity;
import com.example.noteplus.R;
import com.example.noteplus.adapters.NotesRvAdapter;
import com.example.noteplus.databinding.FragmentAllNotesBinding;
import com.example.noteplus.models.Note;
import com.example.noteplus.ui.main.MainFragment;
import com.example.noteplus.ui.note.NoteFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AllNotesFragment extends Fragment {

    private FragmentAllNotesBinding binding;
    private MainActivity mainActivity;
    private AllNotesViewModel allNotesViewModel;
    private RecyclerView recyclerView;
    private NavController navController;


    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }




    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentAllNotesBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment_container);
        recyclerView = binding.allNotesRv;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        allNotesViewModel = new ViewModelProvider(this).get(AllNotesViewModel.class);
        allNotesViewModel.getNoteListLiveData().observe(getViewLifecycleOwner(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                NotesRvAdapter adapter = new NotesRvAdapter(requireContext(), notes, new NotesRvAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Note note) {
                        if (savedInstanceState == null) {
                            allNotesViewModel.setNoteMutableLiveData(note);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("note", note);
                            Fragment fragInstance;
                            fragInstance = NoteFragment.newInstance();
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

        binding.fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noteCreate();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void noteCreate() {
        FragmentContainerView fragmentContainerView = requireActivity().findViewById(R.id.fragment_container);
        fragmentContainerView.setVisibility(View.VISIBLE);
        NoteFragment noteFragment = new NoteFragment();
        getFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, noteFragment)
                .addToBackStack(null)
                .commit();


    }
}