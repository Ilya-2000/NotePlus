package com.example.noteplus;

import android.os.Bundle;

import com.example.noteplus.adapters.MainViewPagerAdapter;
import com.example.noteplus.ui.all_notes.AllNotesFragment;
import com.example.noteplus.ui.main.MainFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import com.example.noteplus.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }
}