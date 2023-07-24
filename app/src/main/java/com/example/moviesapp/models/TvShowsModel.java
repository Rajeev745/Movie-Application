package com.example.moviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class TvShowsModel implements Parcelable {

    public static final Parcelable.Creator<TvShowsModel> CREATOR = new Parcelable.Creator<TvShowsModel>() {
        @Override
        public TvShowsModel createFromParcel(Parcel in) {
            return new TvShowsModel(in);
        }

        @Override
        public TvShowsModel[] newArray(int size) {
            return new TvShowsModel[size];
        }
    };
    private String backdrop_path;
    @SerializedName("first_air_date")
    private String firstAirDate;
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("id")
    private int id;
    private String name;
    @SerializedName("origin_country")
    private List<String> originCountry;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_name")
    private String originalName;
    private String overview;
    private float popularity;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("vote_count")
    private int voteCount;
    private int number_of_episodes;
    private int number_of_seasons;

    public TvShowsModel(
            String backdropPath,
            String firstAirDate,
            List<Integer> genreIds,
            int id,
            String name,
            List<String> originCountry,
            String originalLanguage,
            String originalName,
            String overview,
            float popularity,
            String posterPath,
            float voteAverage,
            int voteCount,
            int number_of_episodes,
            int number_of_seasons
    ) {
        this.backdrop_path = backdropPath;
        this.firstAirDate = firstAirDate;
        this.genreIds = genreIds;
        this.id = id;
        this.name = name;
        this.originCountry = originCountry;
        this.originalLanguage = originalLanguage;
        this.originalName = originalName;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
    }

    protected TvShowsModel(Parcel in) {
        backdrop_path = in.readString();
        firstAirDate = in.readString();
        genreIds = new ArrayList<>();
        in.readList(genreIds, Integer.class.getClassLoader());
        id = in.readInt();
        name = in.readString();
        originCountry = new ArrayList<>();
        in.readList(originCountry, String.class.getClassLoader());
        originalLanguage = in.readString();
        originalName = in.readString();
        overview = in.readString();
        popularity = in.readFloat();
        posterPath = in.readString();
        voteAverage = in.readFloat();
        voteCount = in.readInt();
        number_of_episodes = in.readInt();
        number_of_seasons = in.readInt();
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    // Getters and setters for the class members

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalName() {
        return originalName;
    }

    public String getOverview() {
        return overview;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public int getVoteCount() {
        return voteCount;
    }

    // Implementation of Parcelable interface
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(backdrop_path);
        dest.writeString(firstAirDate);
        dest.writeList(genreIds);
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeList(originCountry);
        dest.writeString(originalLanguage);
        dest.writeString(originalName);
        dest.writeString(overview);
        dest.writeFloat(popularity);
        dest.writeString(posterPath);
        dest.writeFloat(voteAverage);
        dest.writeInt(voteCount);
        dest.writeInt(number_of_episodes);
        dest.writeInt(number_of_seasons);
    }
}
