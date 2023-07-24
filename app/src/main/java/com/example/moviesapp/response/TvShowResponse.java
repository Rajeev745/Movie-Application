package com.example.moviesapp.response;

import com.example.moviesapp.models.TvShowsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowResponse {

    @SerializedName("results")
    @Expose
    TvShowsModel tvShowsModel;

    public TvShowsModel getTvShowsModel() {
        return tvShowsModel;
    }

    @Override
    public String toString() {
        return "TvShowResponse{" +
                "tvShowsModel=" + tvShowsModel +
                '}';
    }
}
