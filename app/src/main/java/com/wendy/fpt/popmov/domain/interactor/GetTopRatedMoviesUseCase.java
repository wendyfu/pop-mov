package com.wendy.fpt.popmov.domain.interactor;

import android.util.Log;

import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMoviesResponse;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;
import com.wendy.fpt.popmov.service.TMDBService;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;
import rx.functions.Func1;

public class GetTopRatedMoviesUseCase extends TMDBServiceUseCase<Void, TMDBMovieDetailsResponse> {

    private final String TAG = getClass().getSimpleName();

    @Inject
    public GetTopRatedMoviesUseCase(
            @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
            @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
            TMDBService tmdbService) {
        super(executionScheduler, postExecutionScheduler, tmdbService);
    }

    @Override
    protected Observable<TMDBMovieDetailsResponse> buildUseCaseObservable(Void parameter) {
        Log.d(TAG, "Retrieve top rated movies");
        return Observable.defer(new Func0<Observable<TMDBMoviesResponse>>() {
            @Override
            public Observable<TMDBMoviesResponse> call() {
                return tmdbService.getTopRatedMovies();
            }
        }).flatMap(new Func1<TMDBMoviesResponse, Observable<TMDBMoviesResponse.MovieResponse>>() {
            @Override
            public Observable<TMDBMoviesResponse.MovieResponse> call(
                    TMDBMoviesResponse tmdbMoviesResponse) {
                Log.d(TAG, tmdbMoviesResponse.toString());
                return Observable.from(tmdbMoviesResponse.getMovies());
            }
        }).flatMap(new Func1<TMDBMoviesResponse.MovieResponse, Observable<TMDBMovieDetailsResponse>>() {
            @Override
            public Observable<TMDBMovieDetailsResponse> call(TMDBMoviesResponse.MovieResponse movieResponse) {
                return tmdbService.getMovieDetails(movieResponse.getMovieId());
            }
        });
    }
}
