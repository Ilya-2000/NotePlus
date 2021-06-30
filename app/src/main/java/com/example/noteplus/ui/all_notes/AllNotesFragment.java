package com.example.noteplus.ui.all_notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.noteplus.MainActivity;
import com.example.noteplus.R;
import com.example.noteplus.adapters.NotesRvAdapter;
import com.example.noteplus.databinding.FragmentAllNotesBinding;
import com.example.noteplus.interfaces.FabInterface;
import com.example.noteplus.models.Note;
import com.example.noteplus.ui.note.NoteFragment;
import com.example.noteplus.ui.note.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class AllNotesFragment extends Fragment implements FabInterface {

    private FragmentAllNotesBinding binding;
    private FloatingActionButton fab;
    private MainActivity mainActivity;
    private AllNotesViewModel allNotesViewModel;
    private RecyclerView recyclerView;

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
        fab = requireActivity().findViewById(R.id.fab);
        recyclerView = binding.allNotesRv;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).setListener(this);
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
                            Fragment fragInstance;

                            //Calling the Fragment newInstance Static method
                            fragInstance = NoteFragment.newInstance();

                            getFragmentManager().beginTransaction()
                                    .add(R.id.container, fragInstance)
                                    .commit();
                        }

                        FragmentContainerView fragmentContainerView = requireActivity().findViewById(R.id.fragment_container);
                        ViewPager2 viewPager2 = requireActivity().findViewById(R.id.main_vp);
                        TabLayout tabLayout = requireActivity().findViewById(R.id.main_tab);
                        fragmentContainerView.setVisibility(View.VISIBLE);
                        NoteFragment noteFragment = new NoteFragment();
                        //getFragmentManager().beginTransaction().replace(R.id.fragment_container, noteFragment).commit();
                        fab.setVisibility(View.GONE);
                        viewPager2.setVisibility(View.GONE);
                        tabLayout.setVisibility(View.GONE);
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

    @Override
    public void noteCreate() {
        FragmentContainerView fragmentContainerView = requireActivity().findViewById(R.id.fragment_container);
        ViewPager2 viewPager2 = requireActivity().findViewById(R.id.main_vp);
        TabLayout tabLayout = requireActivity().findViewById(R.id.main_tab);
        fragmentContainerView.setVisibility(View.VISIBLE);
        NoteFragment noteFragment = new NoteFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, noteFragment).commit();
        fab.setVisibility(View.GONE);
        viewPager2.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);

    }

    @Override
    public void todoCreate() {
        //do nothing
    }
}