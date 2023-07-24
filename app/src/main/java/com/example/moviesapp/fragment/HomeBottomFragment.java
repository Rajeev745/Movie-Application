package com.example.moviesapp.fragment;

import static com.example.moviesapp.utils.Constants.MOVIE_MORE_FRAGMENT;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesapp.MovieActivity;
import com.example.moviesapp.adapter.MovieHomeRecyclerViewAdapter;
import com.example.moviesapp.adapter.MovieOnClickListener;
import com.example.moviesapp.adapter.TvShowRecyclerViewAdapter;
import com.example.moviesapp.databinding.FragmentHomeBottomBinding;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.models.TvShowsModel;
import com.example.moviesapp.viewmodel.MovieViewModel;
import com.example.moviesapp.viewmodel.TvViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeBottomFragment extends Fragment implements MovieOnClickListener {

    private static String TAG = "HomeBottomFragment";
    FragmentHomeBottomBinding binding;
    TvViewModel tvViewModel;
    MovieViewModel movieViewModel;
    List<TvShowsModel> popularTvShow;
    List<TvShowsModel> topRatedTvShow;
    List<TvShowsModel> airingTvShow;
    List<TvShowsModel> onAiringTvShow;
    List<MovieModel> popularMovie;
    List<MovieModel> topRatedMovie;
    List<MovieModel> upcomingMovie;
    MovieHomeRecyclerViewAdapter movieHomeRecyclerViewAdapter;
    TvShowRecyclerViewAdapter tvShowRecyclerViewAdapter;
    int currentPosition = 0;
    long scrollDelayMillis = 2000L; // 2 seconds
    Handler handler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBottomBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        tvViewModel = new ViewModelProvider(this).get(TvViewModel.class);
        initialize();
        makeRequest();
        observeChange();
        moveToMore();

        Runnable scrollRunnable = new Runnable() {
            @Override
            public void run() {
                // Scroll to the next position
                currentPosition++;
                if (currentPosition >= movieHomeRecyclerViewAdapter.getItemCount()) {
                    currentPosition = 0; // Reset back to the beginning
                }
                binding.swipeableRecyclerView.scrollToPosition(currentPosition);
                handler.postDelayed(this, scrollDelayMillis);
            }
        };

        handler.postDelayed(scrollRunnable, scrollDelayMillis);
    }

    private void moveToMore() {
        binding.popularMoreText.setOnClickListener(view -> {
            moveToActivity("popular");
        });

        binding.upcomingMoreText.setOnClickListener(view -> {
            moveToActivity("top_rated");
        });
    }

    private void moveToActivity(String fragmentName) {
        Intent intent = new Intent(getActivity(), MovieActivity.class);
        intent.putExtra(MOVIE_MORE_FRAGMENT, fragmentName);
        startActivity(intent);
    }

    private void initialize() {
        popularMovie = new ArrayList<>();
        popularTvShow = new ArrayList<>();
        topRatedTvShow = new ArrayList<>();
        airingTvShow = new ArrayList<>();
        onAiringTvShow = new ArrayList<>();
        topRatedMovie = new ArrayList<>();
        upcomingMovie = new ArrayList<>();
    }

    private void makeRequest() {
        movieViewModel.searchPopularMovie(1, 2);
        movieViewModel.searchUpcomingMovie(1, 3);
        movieViewModel.searchTopRated(1, 4);
        tvViewModel.searchPopularTvList(1, 2);
        tvViewModel.searchTodayAiringTvList(1, 4);
        tvViewModel.searchTopRatedTvList(1, 3);
        tvViewModel.sarchOnAiringTvList(1, 5);
    }

    @SuppressLint("NotifyDataSetChanged")
    private void observeChange() {
        movieViewModel.getPopularMovieList().observe(getViewLifecycleOwner(), movieModels -> {
            if (movieModels != null) {
                popularMovie.addAll(movieModels);
                movieHomeRecyclerViewAdapter = new MovieHomeRecyclerViewAdapter(popularMovie, getContext(), this, 0);
                movieHomeRecyclerViewAdapter.notifyDataSetChanged();
                binding.popularMovieRecyclerView.setAdapter(movieHomeRecyclerViewAdapter);
            }
        });

        movieViewModel.getUpcomingMovieList().observe(getViewLifecycleOwner(), movieModels -> {
            if (movieModels != null) {
                upcomingMovie.addAll(movieModels);
                movieHomeRecyclerViewAdapter = new MovieHomeRecyclerViewAdapter(upcomingMovie, getContext(), this, 0);
                movieHomeRecyclerViewAdapter.notifyDataSetChanged();
                binding.upcomingMovieRecyclerView.setAdapter(movieHomeRecyclerViewAdapter);
            }
        });

        movieViewModel.getTopRatedMovieList().observe(getViewLifecycleOwner(), movieModels -> {
            if (movieModels != null) {
                topRatedMovie.addAll(movieModels);
                movieHomeRecyclerViewAdapter = new MovieHomeRecyclerViewAdapter(topRatedMovie, getContext(), this, 1);
                movieHomeRecyclerViewAdapter.notifyDataSetChanged();
                binding.swipeableRecyclerView.setAdapter(movieHomeRecyclerViewAdapter);
            }
        });

        tvViewModel.getPopularTvList().observe(getViewLifecycleOwner(), tvShowsModels -> {
            if (tvShowsModels != null) {
                popularTvShow.addAll(tvShowsModels);
                tvShowRecyclerViewAdapter = new TvShowRecyclerViewAdapter(popularTvShow, getContext(), this);
                tvShowRecyclerViewAdapter.notifyDataSetChanged();
                binding.popularShowsRecyclerView.setAdapter(tvShowRecyclerViewAdapter);
            }
        });

        tvViewModel.getAiringTodayTvList().observe(getViewLifecycleOwner(), tvShowsModels -> {
            if (tvShowsModels != null) {
                airingTvShow.addAll(tvShowsModels);
                tvShowRecyclerViewAdapter = new TvShowRecyclerViewAdapter(airingTvShow, getContext(), this);
                tvShowRecyclerViewAdapter.notifyDataSetChanged();
                binding.todayAiringShowsRecyclerView.setAdapter(tvShowRecyclerViewAdapter);
            }
        });

        tvViewModel.getOnAirTvList().observe(getViewLifecycleOwner(), tvShowsModels -> {
            if (tvShowsModels != null) {
                onAiringTvShow.addAll(tvShowsModels);
                tvShowRecyclerViewAdapter = new TvShowRecyclerViewAdapter(onAiringTvShow, getContext(), this);
                tvShowRecyclerViewAdapter.notifyDataSetChanged();
                binding.airingOnShowsRecyclerView.setAdapter(tvShowRecyclerViewAdapter);
            }
        });

        tvViewModel.getTopRatedTvList().observe(getViewLifecycleOwner(), tvShowsModels -> {
            if (tvShowsModels != null) {
                topRatedTvShow.addAll(tvShowsModels);
                tvShowRecyclerViewAdapter = new TvShowRecyclerViewAdapter(topRatedTvShow, getContext(), this);
                tvShowRecyclerViewAdapter.notifyDataSetChanged();
                binding.topratedShowsRecyclerView.setAdapter(tvShowRecyclerViewAdapter);
            }
        });
    }

    @Override
    public void onItemClick(MovieModel movieDetail) {

    }
}