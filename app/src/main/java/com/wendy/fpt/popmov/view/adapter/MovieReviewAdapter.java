package com.wendy.fpt.popmov.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;
import com.wendy.fpt.popmov.view.viewholder.MovieReviewViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewViewHolder> {

    private List<TMDBMovieReviewsResponse.MovieReview> dataset;

    public MovieReviewAdapter() {
        this.dataset = new ArrayList<>();
    }

    @Override
    public MovieReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_mov_reviews, parent, false);
        return new MovieReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewViewHolder holder, int position) {
        TMDBMovieReviewsResponse.MovieReview review = dataset.get(position);
        holder.bindReview(review.getAuthor(), review.getContent());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(TMDBMovieReviewsResponse.MovieReview movieReview) {
        dataset.add(movieReview);
        notifyItemChanged(getItemCount() - 1);
    }
}
