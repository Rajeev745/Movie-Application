package com.example.moviesapp.requests;

import com.example.moviesapp.utils.Credentials;
import com.example.moviesapp.utils.MovieApi;
import com.example.moviesapp.utils.TvShowApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitSingleton {

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl(Credentials.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static TvShowApi tvShowApi = retrofit.create(TvShowApi.class);
    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    public static TvShowApi getTvShowApi() {
        return tvShowApi;
    }

    public static MovieApi getMovieApi() {
        return movieApi;
    }
}
