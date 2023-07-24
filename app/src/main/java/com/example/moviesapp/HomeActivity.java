package com.example.moviesapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moviesapp.adapter.MovieOnClickListener;
import com.example.moviesapp.databinding.ActivityHomeBinding;
import com.example.moviesapp.fragment.HomeBottomFragment;
import com.example.moviesapp.models.MovieModel;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements MovieOnClickListener {

    private static final String TAG = "HomeActivity";
    ActivityHomeBinding binding;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hideStatusBar();
        setUpActionBar();
        getSupportFragmentManager().beginTransaction().add(R.id.bottom_fragment_container, new HomeBottomFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void hideStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            window.getInsetsController().hide(WindowInsets.Type.statusBars());
        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.homeActivityToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("MovieMania");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchView = new SearchView(getSupportActionBar().getThemedContext());
        binding.homeActivityToolbar.addView(searchView);
        searchView.setOnClickListener(view -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onItemClick(MovieModel movieDetail) {

    }
}