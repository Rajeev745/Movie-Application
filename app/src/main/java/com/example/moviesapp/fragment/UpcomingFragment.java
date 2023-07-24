package com.example.moviesapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.moviesapp.MovieDetailActivity;
import com.example.moviesapp.adapter.MovieOnClickListener;
import com.example.moviesapp.adapter.MovieRecyclerViewAdapter;
import com.example.moviesapp.databinding.FragmentUpcomingBinding;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.utils.Constants;
import com.example.moviesapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class UpcomingFragment extends Fragment implements MovieOnClickListener {

    private MovieViewModel movieViewModel;
    List<MovieModel> movieList;
    MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    FragmentUpcomingBinding binding;
    private static String TAG = "UpcomingFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUpcomingBinding.inflate(inflater, container, false);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieList = new ArrayList<>();
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(movieList, getContext(), this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ObserverChange();
        movieViewModel.searchUpcomingMovie( 1, 4);
        binding.recyclerViewUpcoming.setAdapter(movieRecyclerViewAdapter);
    }

    private void ObserverChange() {
        movieViewModel.getUpcomingMovieList().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    movieList.addAll(movieModels);
                    movieRecyclerViewAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public void onItemClick(MovieModel movieDetail) {
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(Constants.MOVIE_DETAIL, movieDetail);
        startActivity(intent);
    }
}