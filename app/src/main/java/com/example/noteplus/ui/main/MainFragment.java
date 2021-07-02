package com.example.noteplus.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.noteplus.R;
import com.example.noteplus.adapters.MainViewPagerAdapter;
import com.example.noteplus.databinding.MainFragmentBinding;
import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainFragment extends Fragment {

    private MainFragmentBinding binding;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private TabLayout tabLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = MainFragmentBinding.inflate(inflater, container, false);
        ViewPager2 viewPager2 = binding.mainVp;
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mainViewPagerAdapter = new MainViewPagerAdapter(this.getFragmentManager(), getLifecycle());
        viewPager2.setAdapter(mainViewPagerAdapter);
        tabLayout = binding.mainTab;
        tabLayout.setBackgroundColor(getResources().getColor(R.color.brutal_blue));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow));

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.all_notes_fragment_label);
            } else {
                tab.setText(R.string.todo_fragment_label);
            }
        })).attach();
        return binding.getRoot();
    }
}