package com.example.moviesapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.moviesapp.databinding.FragmentTopRatedBinding;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.utils.Constants;
import com.example.moviesapp.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopRatedFragment extends Fragment implements MovieOnClickListener {

    private MovieViewModel movieViewModel;
    private MovieRecyclerViewAdapter movieRecyclerViewAdapter;
    private List<MovieModel> movieList;
    FragmentTopRatedBinding binding;
    private static String TAG = "TopRatedFragment";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTopRatedBinding.inflate(inflater, container, false);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        movieList = new ArrayList<>();
        Log.d(TAG, "onView");
        movieRecyclerViewAdapter = new MovieRecyclerViewAdapter(movieList, getContext(), this);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ObserverChange();
        movieViewModel.searchTopRated( 1, 3);
        binding.recyclerViewTopRated.setAdapter(movieRecyclerViewAdapter);
    }

    private void ObserverChange() {
        movieViewModel.getTopRatedMovieList().observe(getViewLifecycleOwner(), movieModels -> {
            if (movieModels != null) {
                movieList.addAll(movieModels);
                movieRecyclerViewAdapter.notifyDataSetChanged();
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