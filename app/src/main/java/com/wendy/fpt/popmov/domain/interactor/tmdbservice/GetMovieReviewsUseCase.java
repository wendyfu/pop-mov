package com.wendy.fpt.popmov.domain.interactor.tmdbservice;

import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;
import com.wendy.fpt.popmov.service.TMDBService;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;
import rx.functions.Func1;

public class GetMovieReviewsUseCase
    extends TMDBServiceUseCase<Integer, TMDBMovieReviewsResponse.MovieReview> {

    @Inject public GetMovieReviewsUseCase(
        @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
        @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
        TMDBService tmdbService) {
        super(executionScheduler, postExecutionScheduler, tmdbService);
    }

    @Override protected Observable<TMDBMovieReviewsResponse.MovieReview> buildUseCaseObservable(
        final Integer parameter) {
        return Observable.defer(new Func0<Observable<TMDBMovieReviewsResponse>>() {
            @Override public Observable<TMDBMovieReviewsResponse> call() {
                return tmdbService.getMovieReviews(parameter);
            }
        })
            .flatMap(
                new Func1<TMDBMovieReviewsResponse, Observable<TMDBMovieReviewsResponse.MovieReview>>() {
                    @Override public Observable<TMDBMovieReviewsResponse.MovieReview> call(
                        TMDBMovieReviewsResponse tmdbMovieReviewsResponse) {
                        return Observable.from(tmdbMovieReviewsResponse.getReviews());
                    }
                });
    }
}
