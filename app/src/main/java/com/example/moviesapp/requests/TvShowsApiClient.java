package com.example.moviesapp.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.AppExecutors;
import com.example.moviesapp.models.TvShowsModel;
import com.example.moviesapp.response.TvShowResponse;
import com.example.moviesapp.response.TvShowSearchResponse;
import com.example.moviesapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class TvShowsApiClient {

    private static final String TAG = "TvShowsApiClient";
    private static TvShowsApiClient instance;
    private MutableLiveData<List<TvShowsModel>> searchTvList;
    private MutableLiveData<List<TvShowsModel>> popularTvList;
    private MutableLiveData<List<TvShowsModel>> topRatedTvList;
    private MutableLiveData<List<TvShowsModel>> airingTodayTvList;
    private MutableLiveData<List<TvShowsModel>> onAirTvList;
    private MutableLiveData<TvShowsModel> tvShow;
    private RequestTvShowsMovieRunnables requestTvShowsMovieRunnables;

    private TvShowsApiClient() {
        searchTvList = new MutableLiveData<>();
        popularTvList = new MutableLiveData<>();
        topRatedTvList = new MutableLiveData<>();
        airingTodayTvList = new MutableLiveData<>();
        onAirTvList = new MutableLiveData<>();
        tvShow = new MutableLiveData<>();
    }

    public static TvShowsApiClient getInstance() {
        if (instance == null) {
            instance = new TvShowsApiClient();
        }
        return instance;
    }

    public MutableLiveData<List<TvShowsModel>> getSearchTvList() {
        return searchTvList;
    }

    public MutableLiveData<List<TvShowsModel>> getPopularTvList() {
        return popularTvList;
    }

    public MutableLiveData<List<TvShowsModel>> getTopRatedTvList() {
        return topRatedTvList;
    }

    public MutableLiveData<List<TvShowsModel>> getAiringTodayTvList() {
        return airingTodayTvList;
    }

    public MutableLiveData<List<TvShowsModel>> getOnAirTvList() {
        return onAirTvList;
    }

    public MutableLiveData<TvShowsModel> getTvShow() {
        return tvShow;
    }

    public void setSearchTvList(String query, int pageNumber, int flagValue) {
        if (requestTvShowsMovieRunnables != null) {
            requestTvShowsMovieRunnables = null;
        }

        requestTvShowsMovieRunnables = new RequestTvShowsMovieRunnables(query, pageNumber, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestTvShowsMovieRunnables);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchPopularTvList(int pageNumber, int flagValue) {
        if (requestTvShowsMovieRunnables != null) {
            requestTvShowsMovieRunnables = null;
        }

        requestTvShowsMovieRunnables = new RequestTvShowsMovieRunnables(pageNumber, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestTvShowsMovieRunnables);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchTopRatedTvList(int pageNumber, int flagValue) {
        if (requestTvShowsMovieRunnables != null) {
            requestTvShowsMovieRunnables = null;
        }

        requestTvShowsMovieRunnables = new RequestTvShowsMovieRunnables(pageNumber, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestTvShowsMovieRunnables);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchTodayAiringTvList(int pageNumber, int flagValue) {
        if (requestTvShowsMovieRunnables != null) {
            requestTvShowsMovieRunnables = null;
        }

        requestTvShowsMovieRunnables = new RequestTvShowsMovieRunnables(pageNumber, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestTvShowsMovieRunnables);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void sarchOnAiringTvList(int pageNumber, int flagValue) {
        if (requestTvShowsMovieRunnables != null) {
            requestTvShowsMovieRunnables = null;
        }

        requestTvShowsMovieRunnables = new RequestTvShowsMovieRunnables(pageNumber, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestTvShowsMovieRunnables);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchTvShow(int id) {
        if (requestTvShowsMovieRunnables != null) {
            requestTvShowsMovieRunnables = null;
        }

        requestTvShowsMovieRunnables = new RequestTvShowsMovieRunnables(id);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestTvShowsMovieRunnables);
        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public class RequestTvShowsMovieRunnables implements Runnable {

        boolean cancelRequest;
        private String query;
        private int pageNumber;
        private int tvShowsId;
        private int flagValue;

        public RequestTvShowsMovieRunnables(String query, int pageNumber, int flagValue) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.flagValue = flagValue;
            cancelRequest = false;
        }

        public RequestTvShowsMovieRunnables(int pageNumber, int flagValue) {
            this.pageNumber = pageNumber;
            this.flagValue = flagValue;
            cancelRequest = false;
        }

        public RequestTvShowsMovieRunnables(int tvShowsId) {
            this.tvShowsId = tvShowsId;
            cancelRequest = false;
        }

        @Override
        public void run() {
            switch (flagValue) {
                case 1: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<TvShowSearchResponse> response = searchTvShows(pageNumber, query).execute();
                        if (response.code() == 200) {
                            List<TvShowsModel> list = new ArrayList<>(response.body().getTvShowList());
                            if (pageNumber == 1) {
                                searchTvList.postValue(list);
                                break;
                            } else {
                                List<TvShowsModel> currentTvShows = searchTvList.getValue();
                                currentTvShows.addAll(list);
                                searchTvList.postValue(currentTvShows);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            searchTvList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        searchTvList.postValue(null);
                    }

                }
                case 2: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<TvShowSearchResponse> response = searchPopularTvShow(pageNumber).execute();
                        if (response.code() == 200) {
                            List<TvShowsModel> list = new ArrayList<>(response.body().getTvShowList());
                            if (pageNumber == 1) {
                                popularTvList.postValue(list);
                                break;
                            } else {
                                List<TvShowsModel> currentTvShows = popularTvList.getValue();
                                currentTvShows.addAll(list);
                                popularTvList.postValue(currentTvShows);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            popularTvList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        popularTvList.postValue(null);
                    }
                }
                case 3: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<TvShowSearchResponse> response = searchTopRatedTvShows(pageNumber).execute();
                        if (response.code() == 200) {
                            List<TvShowsModel> list = new ArrayList<>(response.body().getTvShowList());
                            if (pageNumber == 1) {
                                topRatedTvList.postValue(list);
                                break;
                            } else {
                                List<TvShowsModel> currentTvShows = topRatedTvList.getValue();
                                currentTvShows.addAll(list);
                                topRatedTvList.postValue(currentTvShows);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            topRatedTvList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        topRatedTvList.postValue(null);
                    }

                }
                case 4: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<TvShowSearchResponse> response = searchAiringTodayTvShows(pageNumber).execute();
                        if (response.code() == 200) {
                            List<TvShowsModel> list = new ArrayList<>(response.body().getTvShowList());
                            if (pageNumber == 1) {
                                airingTodayTvList.postValue(list);
                                break;
                            } else {
                                List<TvShowsModel> currentTvShows = airingTodayTvList.getValue();
                                currentTvShows.addAll(list);
                                airingTodayTvList.postValue(currentTvShows);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            airingTodayTvList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        airingTodayTvList.postValue(null);
                    }
                }
                case 5: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<TvShowSearchResponse> response = searchOnTheAirTvShows(pageNumber).execute();
                        if (response.code() == 200) {
                            List<TvShowsModel> list = new ArrayList<>(response.body().getTvShowList());
                            if (pageNumber == 1) {
                                onAirTvList.postValue(list);
                                break;
                            } else {
                                List<TvShowsModel> currentTvShows = onAirTvList.getValue();
                                currentTvShows.addAll(list);
                                onAirTvList.postValue(currentTvShows);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            onAirTvList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        onAirTvList.postValue(null);
                    }
                }
                default: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<TvShowResponse> response = searchTvShowWithId(tvShowsId).execute();
                        if (response.code() == 200) {
                            TvShowsModel tvShowResponse = response.body().getTvShowsModel();
                            tvShow.postValue(tvShowResponse);
                        } else {
                            tvShow.postValue(null);
                        }
                    } catch (IOException e) {
                        tvShow.postValue(null);
                        e.printStackTrace();
                    }
                }
            }
        }

        private Call<TvShowSearchResponse> searchPopularTvShow(int pageNumber) {
            return RetrofitSingleton.getTvShowApi().searchPopularTvShows(Credentials.API_KEY, pageNumber);
        }

        private Call<TvShowSearchResponse> searchAiringTodayTvShows(int pageNumber) {
            return RetrofitSingleton.getTvShowApi().searchAiringTodayTvShows(Credentials.API_KEY, pageNumber);
        }

        private Call<TvShowSearchResponse> searchTopRatedTvShows(int pageNumber) {
            return RetrofitSingleton.getTvShowApi().searchTopRatedTvShows(Credentials.API_KEY, pageNumber);
        }

        private Call<TvShowSearchResponse> searchOnTheAirTvShows(int pageNumber) {
            return RetrofitSingleton.getTvShowApi().searchOnTheAirTvShows(Credentials.API_KEY, pageNumber);
        }

        private Call<TvShowSearchResponse> searchTvShows(int pageNumber, String query) {
            return RetrofitSingleton.getTvShowApi().getTvSearchResponse(Credentials.API_KEY, query, pageNumber);
        }

        private Call<TvShowResponse> searchTvShowWithId(int id) {
            return RetrofitSingleton.getTvShowApi().getShowById(id, Credentials.API_KEY);
        }
    }
}
