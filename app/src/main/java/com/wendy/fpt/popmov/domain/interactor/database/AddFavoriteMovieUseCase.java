package com.wendy.fpt.popmov.domain.interactor.database;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.wendy.fpt.popmov.data.contract.PopMovDBContract;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;

public class AddFavoriteMovieUseCase extends ContentResolverUseCase<TMDBMovieDetailsResponse, Uri> {

    @Inject public AddFavoriteMovieUseCase(
        @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
        @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
        ContentResolver contentResolver) {
        super(executionScheduler, postExecutionScheduler, contentResolver);
    }

    @Override
    protected Observable<Uri> buildUseCaseObservable(final TMDBMovieDetailsResponse movie) {
        return Observable.defer(new Func0<Observable<Uri>>() {
            @Override public Observable<Uri> call() {
                ContentValues values = new ContentValues();
                values.put(PopMovDBContract.FavMovie.COL_MOVIE_ID, movie.getId());
                values.put(PopMovDBContract.FavMovie.COL_TITLE, movie.getTitle());
                values.put(PopMovDBContract.FavMovie.COL_POSTER, movie.getPoster());
                values.put(PopMovDBContract.FavMovie.COL_DURATION, movie.getDuration());
                values.put(PopMovDBContract.FavMovie.COL_OVERVIEW, movie.getOverview());
                values.put(PopMovDBContract.FavMovie.COL_RATING, movie.getRating());
                values.put(PopMovDBContract.FavMovie.COL_RELEASE_DATE, movie.getReleaseDate());

                Uri uri = contentResolver.insert(PopMovDBContract.FavMovie.CONTENT_URI, values);
                return Observable.just(uri);
            }
        });
    }
}
