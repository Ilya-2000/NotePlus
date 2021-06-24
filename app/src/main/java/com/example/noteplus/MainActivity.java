package com.example.noteplus;

import android.os.Bundle;

import com.example.noteplus.adapters.MainViewPagerAdapter;
import com.example.noteplus.interfaces.FabInterface;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.ui.AppBarConfiguration;
import androidx.viewpager2.widget.ViewPager2;

import com.example.noteplus.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    FabInterface fabInterface;

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private MainViewPagerAdapter mainViewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewPager2 viewPager2 = binding.mainVp;
        viewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(mainViewPagerAdapter);
        tabLayout = binding.mainTab;
        tabLayout.setBackgroundColor(getResources().getColor(R.color.brutal_blue));
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.yellow));

        new TabLayoutMediator(tabLayout, viewPager2, ((tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.all_notes_fragment_label);
            } else {
                tab.setText(R.string.folders_fragment_label);
            }
        })).attach();



        setSupportActionBar(binding.toolbar);

        /*NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);*/

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                switch (viewPager2.getCurrentItem()) {
                    case 0:
                        fabInterface.noteCreate();
                        break;
                    case 1:
                        fabInterface.folderCreate();
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setListener(FabInterface iFab) {
        fabInterface = iFab;
    }


   /* @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }*/
}