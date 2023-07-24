package com.example.moviesapp.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {

    private int id;
    private boolean adult;
    private String poster_path;
    private String original_language;
    private String title;
    private float vote_average;
    private float vote_count;
    private String release_date;
    private String overview;
    private String backdrop_path;

    public int getId() {
        return id;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public float getVote_average() {
        return vote_average;
    }

    public float getVote_count() {
        return vote_count;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public MovieModel(int id, boolean adult, String poster_path, String original_language, String title,
                      float vote_average, float vote_count, String release_date, String overview,
                      String backdrop_path) {
        this.id = id;
        this.adult = adult;
        this.poster_path = poster_path;
        this.original_language = original_language;
        this.title = title;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.release_date = release_date;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
    }

    // Getters and Setters (if needed) for the private fields

    // Parcelable implementation
    protected MovieModel(Parcel in) {
        id = in.readInt();
        adult = in.readByte() != 0;
        poster_path = in.readString();
        original_language = in.readString();
        title = in.readString();
        vote_average = in.readFloat();
        vote_count = in.readFloat();
        release_date = in.readString();
        overview = in.readString();
        backdrop_path = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (adult ? 1 : 0));
        dest.writeString(poster_path);
        dest.writeString(original_language);
        dest.writeString(title);
        dest.writeFloat(vote_average);
        dest.writeFloat(vote_count);
        dest.writeString(release_date);
        dest.writeString(overview);
        dest.writeString(backdrop_path);
    }
}
