package com.wendy.fpt.popmov.view;

import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;

public interface MovieDetailView {

    void addVideo(TMDBMovieVideosResponse.MovieVideo video);

    void addReview(TMDBMovieReviewsResponse.MovieReview movieReview);

    void setProgressBarVisibility(boolean isVisible);

    void enableMarkAsFavorite(boolean isFavorite);

    void showToastActionFavorite(boolean isFavorite);
}
