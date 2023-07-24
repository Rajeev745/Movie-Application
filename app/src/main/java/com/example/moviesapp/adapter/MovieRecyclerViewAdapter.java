package com.example.moviesapp.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieModel;

import java.util.List;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<MovieModel> moviesList;
    Context context;
    private static String TAG = "MovieRecyclerViewAdapter";
    private MovieOnClickListener movieOnClickListener;

    public MovieRecyclerViewAdapter(List<MovieModel> moviesList, Context context, MovieOnClickListener movieOnClickListener) {
        this.moviesList = moviesList;
        this.context = context;
        this.movieOnClickListener = movieOnClickListener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_movie_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movieDetail = moviesList.get(position);
        String movieName = movieDetail.getTitle();
        holder.movieName.setText(movieName);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + movieDetail.getPoster_path())
                .into(holder.movieImage);

        setUpRatingBar(holder, movieDetail);

        holder.itemView.setOnClickListener(view -> movieOnClickListener.onItemClick(movieDetail));
    }

    public void setUpRatingBar(ViewHolder holder, MovieModel movieModel) {
        holder.ratingBar.setNumStars(5);
        float ratingValueFrom0To10 = movieModel.getVote_average();
        float maxRating = 10.0f;
        float maxStars = 5.0f;

        float mappedRating = (ratingValueFrom0To10 / maxRating) * maxStars;
        holder.ratingBar.setRating(mappedRating);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
