package com.example.moviesapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.moviesapp.databinding.ActivityMovieDetailBinding;
import com.example.moviesapp.enums.EnumUtilityFunctions;
import com.example.moviesapp.enums.Language;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.utils.Constants;

import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {

    ActivityMovieDetailBinding binding;
    MovieModel movieDetail;
    private static final String TAG = "MovieDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMovieDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        movieDetail = intent.getParcelableExtra(Constants.MOVIE_DETAIL);

        hideStatusBar();
        setUpActionBar();
        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, movieDetail.getTitle());
    }

    @SuppressLint("SetTextI18n")
    private void setUpViews() {
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movieDetail.getPoster_path()).into(binding.imagePosterPath);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movieDetail.getBackdrop_path()).into(binding.imageBackdropPath);
        binding.movieTitle.setText(movieDetail.getTitle());
        if (movieDetail.isAdult()) {
            binding.ratedText.setVisibility(View.VISIBLE);
        }
        binding.overviewText.setText(movieDetail.getOverview());
        binding.releaseDate.setText("Release Date: " + movieDetail.getRelease_date());
        binding.totalVotesText.setText(" Total Votes: " + movieDetail.getVote_count());
        setLanguageText(movieDetail.getOriginal_language());
        setUpRatings();
    }

    @SuppressLint("ResourceAsColor")
    private void setUpRatings(){
        float ratingValueFrom0To10 = movieDetail.getVote_average();
        float maxRating = 10.0f;
        float maxStars = 5.0f;

        float mappedRating = (ratingValueFrom0To10 / maxRating) * maxStars;
        binding.ratingBar.setRating(mappedRating);
    }

    private void setLanguageText(String languageCode) {
        Language language = getLanguageEnumByCode(languageCode);

        if (language != null) {
            String languageName = new EnumUtilityFunctions().getLanguageName(language);
            binding.languageText.setText(languageName);
        } else {
            binding.languageText.setText("Unknown language");
        }
    }

    private Language getLanguageEnumByCode(String languageCode) {
        for (Language language : Language.values()) {
            if (language.getCode().equalsIgnoreCase(languageCode)) {
                return language;
            }
        }
        return null;
    }

    private void setUpActionBar() {
        setSupportActionBar(binding.movieDetailToolbar);
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

}