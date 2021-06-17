package com.example.noteplus.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.noteplus.ui.all.AllNotesFragment;
import com.example.noteplus.ui.folders.FoldersFragment;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentStateAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new FoldersFragment();
        }
        return new AllNotesFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}