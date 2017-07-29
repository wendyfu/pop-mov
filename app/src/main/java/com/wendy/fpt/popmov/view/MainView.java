package com.wendy.fpt.popmov.view;

import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;

public interface MainView {

    void addMovie(TMDBMovieDetailsResponse tmdbMovieDetailsResponse);

    void setErrorVisibility(boolean isVisible);

    void setProgressBarVisibility(boolean isVisible);
}
