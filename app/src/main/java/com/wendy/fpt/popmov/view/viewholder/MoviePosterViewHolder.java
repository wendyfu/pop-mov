package com.wendy.fpt.popmov.view.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wendy.fpt.popmov.R;
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

    public void bind(String imgUrl) {
//        Glide.with(context).load(imgUrl).into(imgMovPoster);
        Glide.with(context).load(R.drawable.sample_poster_1).into(imgMovPoster);
    }

    public void setListener(final MoviePosterAdapter.MoviePosterClickListener listener) {
        imgMovPoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.onClick();
            }
        });
    }
}
