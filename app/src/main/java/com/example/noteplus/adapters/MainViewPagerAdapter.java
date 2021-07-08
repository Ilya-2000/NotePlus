package com.example.noteplus.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.example.noteplus.ui.all_todo.AllTodoFragment;
import com.example.noteplus.ui.main.MainFragment;
//Адаптер главного ViewPager
public class MainViewPagerAdapter extends FragmentStateAdapter {

    public MainViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                AllNotesFragment allNotesFragment = new AllNotesFragment();
                return allNotesFragment;
            case 1:
                AllTodoFragment allTodoFragment = new AllTodoFragment();
                return allTodoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
