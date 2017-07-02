package com.wendy.fpt.popmov.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.view.viewholder.MoviePosterViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterViewHolder> {

    private MoviePosterClickListener listener;
    private List<TMDBMovieDetailsResponse> dataset;

    public MoviePosterAdapter(MoviePosterClickListener listener) {
        this.listener = listener;
        this.dataset = new ArrayList<>();
    }

    @Override
    public MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_mov_poster, parent, false);
        return new MoviePosterViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(MoviePosterViewHolder holder, int position) {
        TMDBMovieDetailsResponse movie = dataset.get(position);
        holder.bindPoster(movie.getPoster());
        holder.setListener(listener, movie);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void addItem(TMDBMovieDetailsResponse movie) {
        dataset.add(movie);
        notifyItemInserted(getItemCount() - 1);
    }

    public void clear() {
        dataset.clear();
        notifyDataSetChanged();
    }

    public interface MoviePosterClickListener {

        void onClick(TMDBMovieDetailsResponse movie);
    }
}
