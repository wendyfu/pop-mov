package com.wendy.fpt.popmov.domain.interactor;

import android.util.Log;

import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;
import com.wendy.fpt.popmov.service.TMDBService;

import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;
import rx.functions.Func1;

public class GetMovieVideosUseCase extends TMDBServiceUseCase<Integer,
        TMDBMovieVideosResponse.MovieVideo> {

    private final String TAG = getClass().getSimpleName();

    public GetMovieVideosUseCase(
            @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
            @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
            TMDBService tmdbService) {
        super(executionScheduler, postExecutionScheduler, tmdbService);
    }

    @Override
    protected Observable<TMDBMovieVideosResponse.MovieVideo> buildUseCaseObservable(
            final Integer parameter) {
        Log.d(TAG, "Retrieve videos for movie id: " + parameter);
        return Observable.defer(new Func0<Observable<TMDBMovieVideosResponse>>() {
            @Override
            public Observable<TMDBMovieVideosResponse> call() {
                return tmdbService.getMovieVideos(parameter);
            }
        }).flatMap(new Func1<TMDBMovieVideosResponse,
                Observable<TMDBMovieVideosResponse.MovieVideo>>() {
            @Override
            public Observable<TMDBMovieVideosResponse.MovieVideo> call(
                    TMDBMovieVideosResponse tmdbMovieVideosResponse) {
                return Observable.from(tmdbMovieVideosResponse.getVideos());
            }
        });
    }
}
