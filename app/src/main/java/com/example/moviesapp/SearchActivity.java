package com.example.moviesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moviesapp.adapter.MovieOnClickListener;
import com.example.moviesapp.adapter.MovieRecyclerViewAdapter;
import com.example.moviesapp.databinding.ActivitySearchBinding;
import com.example.moviesapp.fragment.PopularFragment;
import com.example.moviesapp.fragment.TopRatedFragment;
import com.example.moviesapp.fragment.UpcomingFragment;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.utils.Constants;
import com.example.moviesapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity implements MovieOnClickListener, TextWatcher {

    ActivitySearchBinding binding;
    MovieViewModel movieViewModel;
    List<MovieModel> movieList;
    MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private static String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieList = new ArrayList<>();
        binding.movieSearchText.addTextChangedListener(this);
        configureRecyclerView();
        ObserveChange();
        hideStatusBar();
        setUpActionBar();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MovieActivity.class);
        startActivity(intent);
        finish();
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.searchActivityToolbar);
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

    private void configureRecyclerView() {
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(movieList, this, this);
        binding.recyclerView.setAdapter(movieRecyclerViewAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void ObserveChange() {
        movieViewModel.getMovieList().observe(this, movieModels -> {
            if (movieModels != null) {
                movieList.addAll(movieModels);
                movieRecyclerViewAdapter.notifyDataSetChanged();
                binding.resultNotFoundLayout.setVisibility(View.INVISIBLE);
            } else {
                binding.resultNotFoundLayout.setVisibility(View.VISIBLE);
            }
        });

        movieViewModel.getMovie().observe(this, movieModel -> {
            if (movieModel != null) {
                movieList.add(movieModel);
                movieRecyclerViewAdapter.notifyDataSetChanged();
                binding.resultNotFoundLayout.setVisibility(View.INVISIBLE);
            } else {
                binding.resultNotFoundLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onItemClick(MovieModel movieDetail) {
         Intent intent = new Intent(this, MovieDetailActivity.class);
         intent.putExtra(Constants.MOVIE_DETAIL, movieDetail);
         startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        //TODO: Nothing to do before text change
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        String movieName = charSequence.toString();
        movieList.removeAll(movieList);
        movieRecyclerViewAdapter.notifyDataSetChanged();
        movieViewModel.searchMovie(movieName, 1, 1);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        String movie = editable.toString();
        movieList.removeAll(movieList);
        movieRecyclerViewAdapter.notifyDataSetChanged();

        try {
            int movieId = Integer.parseInt(movie);
            movieViewModel.searchMovieWithId(movieId);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        Log.d(TAG, editable.toString());
    }


}