package com.wendy.fpt.popmov.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wendy.fpt.popmov.BuildConfig;
import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;
import com.wendy.fpt.popmov.presenter.MovieDetailPresenter;
import com.wendy.fpt.popmov.view.MovieDetailView;
import com.wendy.fpt.popmov.view.adapter.MovieReviewAdapter;
import com.wendy.fpt.popmov.view.adapter.MovieVideoAdapter;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wendy.fpt.popmov.PopMovApplication.getCurrentApplication;

public class MovieDetailFragment extends Fragment
    implements MovieDetailView, MovieVideoAdapter.MovieVideoClickListener {

    private static final String MOVIE_DURATION_SUFFIX = " min";
    private static final String MOVIE_RATING_SUFFIX = "/10";

    @BindView(R.id.movie_poster) ImageView moviePoster;
    @BindView(R.id.movie_title) TextView movieTitle;
    @BindView(R.id.movie_year) TextView movieYear;
    @BindView(R.id.movie_duration) TextView movieDuration;
    @BindView(R.id.movie_rating) TextView movieRating;
    @BindView(R.id.movie_overview) TextView movieOverView;
    @BindView(R.id.button_favorite) Button buttonFavorite;
    @BindView(R.id.rv_mov_videos) RecyclerView rvMovVideos;
    @BindView(R.id.rv_mov_reviews) RecyclerView rvMovReviews;
    @BindView(R.id.progress_bar) ProgressBar progressBar;

    @Inject MovieDetailPresenter presenter;

    private MovieVideoAdapter movieVideoAdapter;
    private MovieReviewAdapter movieReviewAdapter;
    private TMDBMovieDetailsResponse movieDetail;
    private boolean isFavorited;

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);
        getCurrentApplication().getAppComponent().inject(this);

        setupRecyclerView();
        presenter.setView(this);
        handleIntent(getActivity().getIntent());
        return view;
    }

    private void setupRecyclerView() {
        movieVideoAdapter = new MovieVideoAdapter(this);
        rvMovVideos.setAdapter(movieVideoAdapter);

        movieReviewAdapter = new MovieReviewAdapter();
        rvMovReviews.setAdapter(movieReviewAdapter);

        rvMovVideos.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovReviews.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void handleIntent(Intent intent) {
        if (!intent.hasExtra(Intent.EXTRA_INTENT)) getActivity().finish();
        movieDetail = Parcels.unwrap(intent.getParcelableExtra(Intent.EXTRA_INTENT));
        setupMovieDetail(movieDetail);
        presenter.checkFavorited(movieDetail.getId());
        presenter.setupMovieVideos(movieDetail.getId());
        presenter.setupMovieReviews(movieDetail.getId());
    }

    private void setupMovieDetail(TMDBMovieDetailsResponse movieDetail) {
        Glide.with(this)
            .load(BuildConfig.TMDB_POSTER_PREFIX + movieDetail.getPoster())
            .into(moviePoster);
        movieTitle.setText(movieDetail.getTitle());
        movieYear.setText(parseMovieReleaseYear(movieDetail.getReleaseDate()));
        movieDuration.setText(movieDetail.getDuration() + MOVIE_DURATION_SUFFIX);
        movieRating.setText(movieDetail.getRating() + MOVIE_RATING_SUFFIX);
        movieOverView.setText(movieDetail.getOverview());
    }

    private String parseMovieReleaseYear(String releaseDate) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(sdf.parse(releaseDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    @Override public void addVideo(TMDBMovieVideosResponse.MovieVideo video) {
        movieVideoAdapter.add(video);
    }

    @Override public void addReview(TMDBMovieReviewsResponse.MovieReview movieReview) {
        movieReviewAdapter.add(movieReview);
    }

    @Override public void setProgressBarVisibility(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override public void enableMarkAsFavorite(boolean isFavorited) {
        buttonFavorite.setText(isFavorited ? getString(R.string.button_unfavorite)
            : getString(R.string.button_mark_as_favorite));
        this.isFavorited = isFavorited;
    }

    @Override public void onClick(TMDBMovieVideosResponse.MovieVideo video) {
        final String YOUTUBE_FORMAT = "https://www.youtube.com/watch?v=%s";
        Uri videoUri = Uri.parse(String.format(YOUTUBE_FORMAT, video.getKey()));
        Intent videoIntent = new Intent(Intent.ACTION_VIEW);
        videoIntent.setData(videoUri);

        if (videoIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(videoIntent);
        }
    }

    @OnClick(R.id.button_favorite) public void onFavoriteClick() {
        if (!isFavorited) {
            presenter.addFavorite(movieDetail);
            return;
        }

        presenter.removeFavorite(movieDetail.getId());
    }

    @Override public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }
}
