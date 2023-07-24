package com.example.moviesapp.requests;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.moviesapp.AppExecutors;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.response.MovieSearchResponse;
import com.example.moviesapp.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Class for requesting the movies and returning the response.
 */
public class MovieApiClient {

    private static MovieApiClient instance;
    private static String TAG = "MoviesApiClient";

    private RequestMovieRunnables requestMovieRunnables;
    // Livedata for movie list
    private MutableLiveData<List<MovieModel>> moviesList;
    private MutableLiveData<List<MovieModel>> popularMoviesList;
    private MutableLiveData<List<MovieModel>> upcomingMoviesList;
    private MutableLiveData<List<MovieModel>> topRatedMoviesList;
    private MutableLiveData<MovieModel> movie;

    private MovieApiClient() {
        movie = new MutableLiveData<>();
        moviesList = new MutableLiveData<>();
        popularMoviesList = new MutableLiveData<>();
        upcomingMoviesList = new MutableLiveData<>();
        topRatedMoviesList = new MutableLiveData<>();
    }

    /**
     * Creating instance of this class
     */
    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    /**
     * Method returning the list of movies to the repository.
     */
    public MutableLiveData<List<MovieModel>> getMoviesList() {
        return moviesList;
    }

    public MutableLiveData<List<MovieModel>> getPopularMoviesList() {
        return popularMoviesList;
    }

    public MutableLiveData<List<MovieModel>> getUpcomingMoviesList() {
        return upcomingMoviesList;
    }

    public MutableLiveData<List<MovieModel>> getTopRatedMoviesList() {
        return topRatedMoviesList;
    }

    public MutableLiveData<MovieModel> getMovie() {
        return movie;
    }

    /**
     * Method that request query for page number.
     *
     * @param query      is the movie to be searched
     * @param pageNumber is the pagenumber for all the results
     * @param flagValue  for type of request
     */
    public void getSearchMovieApi(String query, int pageNumber, int flagValue) {

        if (requestMovieRunnables != null) {
            requestMovieRunnables = null;
        }
        // Invocation of requestMovieRunnable class
        requestMovieRunnables = new RequestMovieRunnables(query, pageNumber, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestMovieRunnables);

        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchMovieWithId(int id) {
        if (requestMovieRunnables != null) {
            requestMovieRunnables = null;
        }
        requestMovieRunnables = new RequestMovieRunnables(id);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestMovieRunnables);

        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchPopularMovie(int page, int flagValue) {
        if (requestMovieRunnables != null) {
            requestMovieRunnables = null;
        }
        requestMovieRunnables = new RequestMovieRunnables(page, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestMovieRunnables);


        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchUpcomingMovie(int page, int flagValue) {
        if (requestMovieRunnables != null) {
            requestMovieRunnables = null;
        }
        requestMovieRunnables = new RequestMovieRunnables(page, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestMovieRunnables);

        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void searchTopRatedMovie(int page, int flagValue) {
        if (requestMovieRunnables != null) {
            requestMovieRunnables = null;
        }
        requestMovieRunnables = new RequestMovieRunnables(page, flagValue);
        final Future mHandler = AppExecutors.getInstance().getNetworkIO().submit(requestMovieRunnables);

        AppExecutors.getInstance().getNetworkIO().schedule(() -> {
            mHandler.cancel(true);
        }, 5, TimeUnit.SECONDS);
    }

    public void setNull() {
        moviesList.postValue(null);
    }

    public class RequestMovieRunnables implements Runnable {

        boolean cancelRequest;
        private String query;
        private int pageNumber;
        private int movieId;
        private int flagValue;

        public RequestMovieRunnables(String query, int pageNumber, int flagValue) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.flagValue = flagValue;
            cancelRequest = false;
        }

        public RequestMovieRunnables(int pageNumber, int flagValue) {
            this.pageNumber = pageNumber;
            this.flagValue = flagValue;
            cancelRequest = false;
        }

        public RequestMovieRunnables(int movieId) {
            this.movieId = movieId;
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
                        Response<MovieSearchResponse> response = getMoviesSearch(query, pageNumber).execute();

                        if (response.code() == 200) {
                            List<MovieModel> list = new ArrayList<>(response.body().getMovies());
                            if (pageNumber == 1) {
                                moviesList.postValue(list);
                            } else {
                                List<MovieModel> currentMovies = moviesList.getValue();
                                currentMovies.addAll(list);
                                moviesList.postValue(currentMovies);
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            moviesList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        moviesList.postValue(null);
                    }
                }
                case 2: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<MovieSearchResponse> response = getPopularSearch(pageNumber).execute();
                        Log.d(TAG, "Number of times function is calling");
                        if (response.code() == 200) {
                            List<MovieModel> list = new ArrayList<>(response.body().getMovies());
                            if (pageNumber == 1) {
                                popularMoviesList.postValue(list);
                                break;
                            } else {
                                List<MovieModel> currentMovies = moviesList.getValue();
                                currentMovies.addAll(list);
                                popularMoviesList.postValue(currentMovies);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            popularMoviesList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        popularMoviesList.postValue(null);
                    }
                }
                case 3: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<MovieSearchResponse> response = getUpcomingSearch(pageNumber).execute();
                        if (response.code() == 200) {
                            List<MovieModel> list = new ArrayList<>(response.body().getMovies());
                            if (pageNumber == 1) {
                                upcomingMoviesList.postValue(list);
                                break;
                            } else {
                                List<MovieModel> currentMovies = moviesList.getValue();
                                currentMovies.addAll(list);
                                upcomingMoviesList.postValue(currentMovies);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            upcomingMoviesList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        upcomingMoviesList.postValue(null);
                    }
                }
                case 4: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<MovieSearchResponse> response = getTopRatedSearch(pageNumber).execute();
                        if (response.code() == 200) {
                            List<MovieModel> list = new ArrayList<>(response.body().getMovies());
                            if (pageNumber == 1) {
                                topRatedMoviesList.postValue(list);
                                break;
                            } else {
                                List<MovieModel> currentMovies = moviesList.getValue();
                                currentMovies.addAll(list);
                                topRatedMoviesList.postValue(currentMovies);
                                break;
                            }
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            topRatedMoviesList.postValue(null);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        topRatedMoviesList.postValue(null);
                    }
                }
                default: {
                    try {
                        if (cancelRequest) {
                            return;
                        }
                        Response<MovieModel> response = getMovieWithId(movieId).execute();
                        if (response.code() == 200) {
                            MovieModel mMovie = response.body();
                            movie.postValue(mMovie);
                            break;
                        } else {
                            Log.v(TAG, "Error message: " + response.errorBody());
                            movie.postValue(null);
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        movie.postValue(null);
                    }
                }
            }

            Log.d(TAG, moviesList.toString());
        }

        /**
         * Creating the request method when the movie is searched.
         *
         * @param query      the movies name or id to be search
         * @param pageNumber page number
         * @return response of movie search result
         */
        private Call<MovieSearchResponse> getMoviesSearch(String query, int pageNumber) {
            return RetrofitSingleton.getMovieApi().searchResponse(Credentials.API_KEY, query, pageNumber);
        }

        private Call<MovieModel> getMovieWithId(int movieId) {
            return RetrofitSingleton.getMovieApi().getMovieWithId(movieId, Credentials.API_KEY);
        }

        private Call<MovieSearchResponse> getPopularSearch(int pageNumber) {
            return RetrofitSingleton.getMovieApi().searchPopularMovie(Credentials.API_KEY, pageNumber);
        }

        private Call<MovieSearchResponse> getUpcomingSearch(int pageNumber) {
            return RetrofitSingleton.getMovieApi().searchUpcomingMovie(Credentials.API_KEY, pageNumber);
        }

        private Call<MovieSearchResponse> getTopRatedSearch(int pageNumber) {
            return RetrofitSingleton.getMovieApi().searchTopRatedMovie(Credentials.API_KEY, pageNumber);
        }
    }
}
