package com.example.moviesapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.models.TvShowsModel;
import com.example.moviesapp.repository.TvShowRepository;

import java.util.List;

public class TvViewModel extends ViewModel {

    TvShowRepository tvShowRepository;

    private TvViewModel() {
        tvShowRepository = TvShowRepository.getInstance();
    }

    public MutableLiveData<List<TvShowsModel>> getSearchTvList() {
        return tvShowRepository.getSearchTvList() ;
    }

    public MutableLiveData<List<TvShowsModel>> getPopularTvList() {
        return tvShowRepository.getPopularTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getTopRatedTvList() {
        return tvShowRepository.getTopRatedTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getAiringTodayTvList() {
        return tvShowRepository.getAiringTodayTvList();
    }

    public MutableLiveData<List<TvShowsModel>> getOnAirTvList() {
        return tvShowRepository.getOnAirTvList();
    }

    public MutableLiveData<TvShowsModel> getTvShow() {
        return tvShowRepository.getTvShow();
    }

    public void searchPopularTvList(int pageNumber, int flagValue) {
        tvShowRepository.searchPopularTvList(pageNumber, flagValue);
    }

    public void searchTopRatedTvList(int pageNumber, int flagValue) {
        tvShowRepository.searchTopRatedTvList(pageNumber, flagValue);
    }

    public void sarchOnAiringTvList(int pageNumber, int flagValue) {
        tvShowRepository.sarchOnAiringTvList(pageNumber, flagValue);
    }

    public void searchTodayAiringTvList(int pageNumber, int flagValue) {
        tvShowRepository.searchTodayAiringTvList(pageNumber, flagValue);
    }

    public void setSearchTvList(String query, int pageNumber, int flagValue) {
        tvShowRepository.setSearchTvList(query, pageNumber, flagValue);
    }

    public void searchTvShow(int id) {
        tvShowRepository.searchTvShow(id);
    }
}
