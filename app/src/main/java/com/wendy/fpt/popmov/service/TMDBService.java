package com.wendy.fpt.popmov.service;

import com.wendy.fpt.popmov.BuildConfig;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;
import com.wendy.fpt.popmov.data.model.TMDBMoviesResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface TMDBService {

    String MOVIE_ID = "movie_id";
    String MOVIES_POPULAR_ENDPOINT = "movie/popular";
    String MOVIES_TOP_RATED_ENDPOINT = "movie/top_rated";
    String MOVIE_DETAILS = "movie/{" + MOVIE_ID + "}";
    String MOVIE_VIDEOS = "movie/{" + MOVIE_ID + "}/videos";
    String MOVIE_REVIEWS = "movie/{" + MOVIE_ID + "}/reviews";

    @GET(MOVIES_POPULAR_ENDPOINT + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMoviesResponse> getPopularMovies();

    @GET(MOVIES_TOP_RATED_ENDPOINT + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMoviesResponse> getTopRatedMovies();

    @GET(MOVIE_DETAILS + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMovieDetailsResponse> getMovieDetails(@Path(MOVIE_ID) int movieId);

    @GET(MOVIE_VIDEOS + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMovieVideosResponse> getMovieVideos(@Path(MOVIE_ID) int movieId);

    @GET(MOVIE_REVIEWS + "?api_key=" + BuildConfig.TMDB_API_KEY)
    Observable<TMDBMovieReviewsResponse> getMovieReviews(@Path(MOVIE_ID) int movieId);
}
