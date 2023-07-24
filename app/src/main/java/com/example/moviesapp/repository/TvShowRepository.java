package com.example.moviesapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.models.TvShowsModel;
import com.example.moviesapp.requests.TvShowsApiClient;

import java.util.List;

public class TvShowRepository {

    private static TvShowRepository instance;
    private TvShowsApiClient tvShowsApiClient;

    private TvShowRepository() {
        tvShowsApiClient = TvShowsApiClient.getInstance();
    }

    public static TvShowRepository getInstance() {
        if (instance == null) {
            instance = new TvShowRepository();
        }
        return instance;
    }

    public MutableLiveData<List<TvShowsModel>> getSearchTvList() {
        return tvShowsApiClient.getSearchTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getPopularTvList() {
        return tvShowsApiClient.getPopularTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getTopRatedTvList() {
        return tvShowsApiClient.getTopRatedTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getAiringTodayTvList() {
        return tvShowsApiClient.getAiringTodayTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getOnAirTvList() {
        return tvShowsApiClient.getOnAirTvList();
    }

    public MutableLiveData<TvShowsModel> getTvShow() {
        return tvShowsApiClient.getTvShow();
    }

    public void searchPopularTvList(int pageNumber, int flagValue) {
        tvShowsApiClient.searchPopularTvList(pageNumber, flagValue);
    }

    public void searchTopRatedTvList(int pageNumber, int flagValue) {
        tvShowsApiClient.searchTopRatedTvList(pageNumber, flagValue);
    }

    public void sarchOnAiringTvList(int pageNumber, int flagValue) {
        tvShowsApiClient.sarchOnAiringTvList(pageNumber, flagValue);
    }

    public void searchTodayAiringTvList(int pageNumber, int flagValue) {
        tvShowsApiClient.searchTodayAiringTvList(pageNumber, flagValue);
    }

    public void setSearchTvList(String query, int pageNumber, int flagValue) {
        tvShowsApiClient.setSearchTvList(query, pageNumber, flagValue);
    }

    public void searchTvShow(int id) {
        tvShowsApiClient.searchTvShow(id);
    }
}
