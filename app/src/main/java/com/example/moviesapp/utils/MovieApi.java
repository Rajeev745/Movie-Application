package com.example.moviesapp.utils;

import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("/3/search/movie")
    Call<MovieSearchResponse> searchResponse(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovieWithId(
            @Path("movie_id") int id,
            @Query("api_key") String apiKey
    );

    @GET("3/movie/popular?")
    Call<MovieSearchResponse> searchPopularMovie(
        @Query("api_key") String key,
        @Query("page") int page
    );

    @GET("3/movie/upcoming?")
    Call<MovieSearchResponse> searchUpcomingMovie(
            @Query("api_key") String key,
            @Query("page") int page
    );

    @GET("3/movie/top_rated?")
    Call<MovieSearchResponse> searchTopRatedMovie(
            @Query("api_key") String key,
            @Query("page") int page
    );
}
