package com.example.moviesapp.utils;

import com.example.moviesapp.response.TvShowResponse;
import com.example.moviesapp.response.TvShowSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TvShowApi {

    @GET("3/search/tv")
    Call<TvShowSearchResponse> getTvSearchResponse(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") int page
    );

    @GET("3/tv/{movie_id}?")
    Call<TvShowResponse> getShowById(
            @Query("id") int id,
            @Query("api_key") String api_key
    );

    @GET("3/tv/popular?")
    Call<TvShowSearchResponse> searchPopularTvShows(
            @Query("api_key") String key,
            @Query("page") int page
    );

    @GET("3/tv/airing_today?")
    Call<TvShowSearchResponse> searchAiringTodayTvShows(
            @Query("api_key") String key,
            @Query("page") int page
    );

    @GET("3/tv/top_rated?")
    Call<TvShowSearchResponse> searchTopRatedTvShows(
            @Query("api_key") String key,
            @Query("page") int page
    );

    @GET("3/tv/on_the_air?")
    Call<TvShowSearchResponse> searchOnTheAirTvShows(
            @Query("api_key") String key,
            @Query("page") int page
    );
}
