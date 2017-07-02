package com.wendy.fpt.popmov.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wendy.fpt.popmov.BuildConfig;
import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.view.adapter.MoviePosterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviePosterViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_mov_poster)
    ImageView imgMovPoster;

    private Context context;

    public MoviePosterViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
    }

    public void bindPoster(String imgUrl) {
        Glide.with(context)
                .load(BuildConfig.TMDB_POSTER_PREFIX + imgUrl)
                .dontAnimate()
                .into(imgMovPoster);
    }

    public void setListener(final MoviePosterAdapter.MoviePosterClickListener listener,
                            final TMDBMovieDetailsResponse movie) {
        imgMovPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.onClick(movie);
            }
        });
    }
}
