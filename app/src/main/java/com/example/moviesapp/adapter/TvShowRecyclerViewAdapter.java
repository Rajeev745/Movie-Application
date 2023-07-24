package com.example.moviesapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.models.MovieModel;
import com.example.moviesapp.models.TvShowsModel;

import java.util.List;

public class TvShowRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    List<TvShowsModel> tvShowList;
    Context context;
    MovieOnClickListener movieOnClickListener;
    private static String TAG = "TvShowRecyclerViewAdapter";

    public TvShowRecyclerViewAdapter(List<TvShowsModel> tvShowList, Context context, MovieOnClickListener movieOnClickListener) {
        this.tvShowList = tvShowList;
        this.context = context;
        this.movieOnClickListener = movieOnClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_show_home_recycler_view_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShowsModel tvShowsModel = tvShowList.get(position);
        String tvShowName = tvShowsModel.getName();
        holder.tvShowName.setText(tvShowName);
        System.out.println("####"+tvShowsModel.getPosterPath());
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + tvShowsModel.getPosterPath())
                .into(holder.tvShowImage);

        setUpRatingBar(holder, tvShowsModel);
    }

    public void setUpRatingBar(ViewHolder holder, TvShowsModel tvShowsModel) {
        float ratingValueFrom0To10 = tvShowsModel.getVoteAverage();
        float maxRating = 10.0f;
        float maxStars = 5.0f;

        float mappedRating = (ratingValueFrom0To10 / maxRating) * maxStars;
        holder.tvShowRatingBar.setRating(mappedRating);
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }
}
