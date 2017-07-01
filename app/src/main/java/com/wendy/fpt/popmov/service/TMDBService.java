package com.wendy.fpt.popmov.service;

import com.wendy.fpt.popmov.BuildConfig;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface TMDBService {

    String MOVIE_ID = "movie_id";
    String MOVIES_POPULAR_ENDPOINT = "movie/popular";
    String MOVIES_TOP_RATED_ENDPOINT = "movie/top_rated";
    String MOVIE_DETAILS = "movie/";

    @GET(MOVIES_POPULAR_ENDPOINT + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMoviesResponse> getPopularMovies();

    @GET(MOVIES_TOP_RATED_ENDPOINT + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMoviesResponse> getTopRatedMovies();

    @GET(MOVIE_DETAILS + "{" + MOVIE_ID + "}" + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMovieDetailsResponse> getMovieDetails(@Path(MOVIE_ID) int movieId);
}
