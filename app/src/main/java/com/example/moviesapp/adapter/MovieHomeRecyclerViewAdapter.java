package com.example.moviesapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieModel;

import java.util.List;

public class MovieHomeRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static String TAG = "MovieHomeRecyclerViewAdapter";
    List<MovieModel> movieList;
    Context context;
    MovieOnClickListener movieOnClickListener;
    int flag;

    public MovieHomeRecyclerViewAdapter(List<MovieModel> movieList, Context context, MovieOnClickListener movieOnClickListener, int flag) {
        this.movieList = movieList;
        this.context = context;
        this.movieOnClickListener = movieOnClickListener;
        this.flag = flag;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (flag == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.swipable_list_item, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_home_recycler_view_item, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieModel movieDetail = movieList.get(position);
        String movieName = movieDetail.getTitle();

        if (flag == 1) {
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + movieDetail.getPoster_path())
                    .into(holder.swipeableImage);
        } else {
            holder.movieHomeName.setText(movieName);
            Glide.with(holder.itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w500/" + movieDetail.getPoster_path())
                    .into(holder.movieHomeImage);
            setUpRatingBar(holder, movieDetail);
        }
    }

    public void setUpRatingBar(ViewHolder holder, MovieModel movieModel) {
        float ratingValueFrom0To10 = movieModel.getVote_average();
        float maxRating = 10.0f;
        float maxStars = 5.0f;

        float mappedRating = (ratingValueFrom0To10 / maxRating) * maxStars;
        holder.movieHomeRatingBar.setRating(mappedRating);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
