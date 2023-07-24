package com.example.moviesapp;

import static com.example.moviesapp.utils.Constants.MOVIE_MORE_FRAGMENT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesapp.databinding.ActivityMovieBinding;
import com.example.moviesapp.fragment.PopularFragment;
import com.example.moviesapp.fragment.TopRatedFragment;
import com.example.moviesapp.fragment.UpcomingFragment;
import com.example.moviesapp.viewmodel.MovieViewModel;

import java.util.Objects;

public class MovieActivity extends AppCompatActivity {

    private static final String TAG = "MovieActivity";
    ActivityMovieBinding binding;
    private MovieViewModel movieViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle dataBundle = getIntent().getExtras();
        if (dataBundle != null) {
            String data = dataBundle.getString(MOVIE_MORE_FRAGMENT);
            Log.d(TAG, data);
            if (Objects.equals(data, "popular")) {
                loadFragment(new PopularFragment());
            } else {
                loadFragment(new UpcomingFragment());
            }
        }
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        hideStatusBar();
        setUpActionBar();
        binding.bottomNavBar.setOnItemSelectedListener(this::navigateToFragment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return navigateToFragment(item);
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.movieActivityToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("MovieMania");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void hideStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.getInsetsController().hide(WindowInsets.Type.statusBars());
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    @SuppressLint("NonConstantResourceId")
    private boolean navigateToFragment(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.popular_fragment:
                loadFragment(new PopularFragment());
                return true;
            case R.id.upcoming_fragment:
                loadFragment(new UpcomingFragment());
                return true;
            case R.id.top_rated_fragment:
                loadFragment(new TopRatedFragment());
                return true;
            default:
                Intent intent = new Intent(MovieActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }
}
