package com.wendy.fpt.popmov.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;
import com.wendy.fpt.popmov.view.viewholder.MovieVideoViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MovieVideoAdapter extends RecyclerView.Adapter<MovieVideoViewHolder> {

    private MovieVideoClickListener listener;
    private List<TMDBMovieVideosResponse.MovieVideo> dataset;

    public MovieVideoAdapter(MovieVideoClickListener listener) {
        this.listener = listener;
        this.dataset = new ArrayList<>();
    }

    @Override
    public MovieVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_mov_videos, parent, false);
        return new MovieVideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieVideoViewHolder holder, int position) {
        TMDBMovieVideosResponse.MovieVideo video = dataset.get(position);
        holder.bindVideo(video.getType(), video.getName());
        holder.setListener(listener, video);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(TMDBMovieVideosResponse.MovieVideo video) {
        dataset.add(video);
        notifyItemChanged(getItemCount() - 1);
    }

    public interface MovieVideoClickListener {

        void onClick(TMDBMovieVideosResponse.MovieVideo video);
    }
}
