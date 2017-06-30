package com.wendy.fpt.popmov.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.view.viewholder.MoviePosterViewHolder;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterViewHolder> {

    private MoviePosterClickListener listener;

    public MoviePosterAdapter(MoviePosterClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_mov_poster, parent, false);
        MoviePosterViewHolder viewHolder = new MoviePosterViewHolder(view, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviePosterViewHolder holder, int position) {
        holder.bind("");
        holder.setListener(listener);
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public interface MoviePosterClickListener {

        void onClick();
    }
}
