package com.wendy.fpt.popmov.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wendy.fpt.popmov.BuildConfig;
import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailFragment extends Fragment {

    private static final String MOVIE_DURATION_SUFFIX = " min";
    private static final String MOVIE_RATING_SUFFIX = "/10";

    @BindView(R.id.movie_poster)
    ImageView moviePoster;
    @BindView(R.id.movie_title)
    TextView movieTitle;
    @BindView(R.id.movie_year)
    TextView movieYear;
    @BindView(R.id.movie_duration)
    TextView movieDuration;
    @BindView(R.id.movie_rating)
    TextView movieRating;
    @BindView(R.id.movie_overview)
    TextView movieOverView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        handleIntent(getActivity().getIntent());
        return view;
    }

    private void handleIntent(Intent intent) {
        if (!intent.hasExtra(Intent.EXTRA_INTENT)) getActivity().finish();
        TMDBMovieDetailsResponse movieDetail = Parcels.unwrap(
                intent.getParcelableExtra(Intent.EXTRA_INTENT));
        setupMovieDetail(movieDetail);
    }

    private void setupMovieDetail(TMDBMovieDetailsResponse movieDetail) {
        Glide.with(this).load(BuildConfig.TMDB_POSTER_PREFIX + movieDetail.getPoster())
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
}
