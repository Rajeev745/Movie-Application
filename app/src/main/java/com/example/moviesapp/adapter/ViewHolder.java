package com.example.moviesapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView movieName;
    TextView movieHomeName;
    TextView tvShowName;
    ImageView movieHomeImage;
    ImageView tvShowImage;
    ImageView movieImage;
    RatingBar movieHomeRatingBar;
    RatingBar tvShowRatingBar;
    RatingBar ratingBar;
    ImageView swipeableImage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        ratingBar = itemView.findViewById(R.id.rating_bar);
        movieImage = itemView.findViewById(R.id.movie_image);
        movieName = itemView.findViewById(R.id.movie_name_text);
        movieHomeName = itemView.findViewById(R.id.movie_home_text);
        movieHomeImage = itemView.findViewById(R.id.home_movie_image_view);
        movieHomeRatingBar = itemView.findViewById(R.id.home_movie_rating_bar);
        tvShowName = itemView.findViewById(R.id.tv_show_home_text);
        tvShowImage = itemView.findViewById(R.id.home_tv_show_image_view);
        tvShowRatingBar = itemView.findViewById(R.id.home_tv_show_rating_bar);
        swipeableImage = itemView.findViewById(R.id.swipable_image);
    }
}