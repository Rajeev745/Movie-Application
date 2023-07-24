package com.example.moviesapp.response;

import com.example.moviesapp.models.TvShowsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowSearchResponse {

    @SerializedName("results")
    @Expose
    List<TvShowsModel> tvShowList;

    @SerializedName("total_results")
    @Expose
    int totalCount;

    public List<TvShowsModel> getTvShowList() {
        return tvShowList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    @Override
    public String toString() {
        return "TvShowSearchResponse{" +
                "tvShowList=" + tvShowList +
                ", totalCount=" + totalCount +
                '}';
    }
}
