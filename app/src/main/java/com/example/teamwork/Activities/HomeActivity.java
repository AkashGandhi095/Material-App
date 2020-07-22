package com.example.teamwork.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.teamwork.Adapter.PagerAdapter;
import com.example.teamwork.Fragments.ContactUsFragment;
import com.example.teamwork.Fragments.ImagesFragment;
import com.example.teamwork.Fragments.ViewImagesFragment;
import com.example.teamwork.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    private Intent intent;
    private MaterialToolbar toolbar;
    private MaterialTextView navHeader_email;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    private ViewPager pager;
    private TabLayout tabLayout;
    private PagerAdapter adapter;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initViews();
        Intent intent = getIntent();
        navHeader_email.setText(intent.getStringExtra("mEmail"));

        navigationView.setNavigationItemSelectedListener(item -> {
            if(item.getItemId() == R.id.logout)
            {
                startActivity(new Intent(this , MainActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    private void initViews() {

        // Setup Material Navigation Drawer Views
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.material_toolbar);
        navigationView = findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        navHeader_email = headerView.findViewById(R.id.header_email);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        drawerToggle = setupDrawerToggle();
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(android.R.color.white));



        // Setup Material tabs with ViewPager

        pager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);

        adapter = new com.example.teamwork.Adapter.PagerAdapter(getSupportFragmentManager());
        adapter.setupFragments(new ContactUsFragment() , "Contact Us");
        adapter.setupFragments(new ImagesFragment() , "Images");
        adapter.setupFragments(new ViewImagesFragment() , "View Images");
        pager.setAdapter(adapter);

    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}