package com.example.moviesapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private static String TAG = "MovieViewModel";

    public MovieViewModel() {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovieList() {
        return movieRepository.getMoviesFromRepository();
    }

    public LiveData<List<MovieModel>> getPopularMovieList() {
        return movieRepository.getPopularMoviesFromRepository();
    }

    public LiveData<List<MovieModel>> getUpcomingMovieList() {
        return movieRepository.getUpcomingMoviesFromRepository();
    }

    public LiveData<List<MovieModel>> getTopRatedMovieList() {
        return movieRepository.getTopRatedMoviesFromRepository();
    }

    public LiveData<MovieModel> getMovie() {
        return movieRepository.getMovie();
    }

    public void searchMovie(String query, int pageNumber, int flagValue) {
        movieRepository.searchMovie(query, pageNumber, flagValue);
    }

    public void searchMovieWithId(int id) {
        movieRepository.searchMovieWithId(id);
    }

    public void searchPopularMovie(int pageNumber, int flagValue) {
        movieRepository.searchPopularMovie(pageNumber, flagValue);
    }

    public void searchTopRated(int pageNumber, int flagValue) {
        movieRepository.searchTopRatedMovie(pageNumber, flagValue);
    }

    public void searchUpcomingMovie(int pageNumber, int flagValue) {
        movieRepository.searchUpcomingMovie(pageNumber, flagValue);
    }
}
