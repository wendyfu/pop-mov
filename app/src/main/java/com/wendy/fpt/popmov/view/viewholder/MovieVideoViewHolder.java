package com.wendy.fpt.popmov.view.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;
import com.wendy.fpt.popmov.view.adapter.MovieVideoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieVideoViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.video_container)
    LinearLayout videoContainer;
    @BindView(R.id.video_type)
    TextView videoType;
    @BindView(R.id.video_name)
    TextView videoName;

    public MovieVideoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bindVideo(String videoType, String videoName) {
        this.videoType.setText(videoType);
        this.videoName.setText(videoName);
    }

    public void setListener(final MovieVideoAdapter.MovieVideoClickListener listener,
                            final TMDBMovieVideosResponse.MovieVideo video) {
        videoContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener == null) return;
                listener.onClick(video);
            }
        });
    }
}
