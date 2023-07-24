package com.example.moviesapp.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.requests.MovieApiClient;

import java.util.List;

public class MovieRepository {

    private static MovieRepository instance;
    private static String TAG = "MovieRepository";

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieApiClient movieApiClient;

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMoviesFromRepository() {
        return movieApiClient.getMoviesList();
    }

    public LiveData<List<MovieModel>> getPopularMoviesFromRepository() {
        return movieApiClient.getPopularMoviesList();
    }

    public LiveData<List<MovieModel>> getUpcomingMoviesFromRepository() {
        return movieApiClient.getUpcomingMoviesList();
    }

    public LiveData<List<MovieModel>> getTopRatedMoviesFromRepository() {
        return movieApiClient.getTopRatedMoviesList();
    }

    public LiveData<MovieModel> getMovie() {
        return movieApiClient.getMovie();
    }

    public void searchMovie(String query, int pageNumber, int flagValue) {
        movieApiClient.getSearchMovieApi(query, pageNumber, flagValue);
    }

    public void searchMovieWithId(int id) {
        movieApiClient.searchMovieWithId(id);
    }

    public void searchPopularMovie(int pageNumber, int flagValue) {
        movieApiClient.searchPopularMovie(pageNumber, flagValue);
    }

    public void searchTopRatedMovie(int pageNumber, int flagValue) {
        movieApiClient.searchTopRatedMovie(pageNumber, flagValue);
    }

    public void searchUpcomingMovie(int pageNumber, int flagValue) {
        movieApiClient.searchUpcomingMovie(pageNumber, flagValue);
    }

}
