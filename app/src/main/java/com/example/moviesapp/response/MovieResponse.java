package com.example.moviesapp.response;

import com.example.moviesapp.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {

    @SerializedName("results")
    @Expose
    private MovieModel movieModel;

    public MovieModel getMovieModel() {
        return movieModel;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movieModel +
                '}';
    }
}
