package com.wendy.fpt.popmov.domain.interactor.database;

import android.content.ContentResolver;
import android.database.Cursor;

import com.wendy.fpt.popmov.data.contract.PopMovDBContract;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;
import rx.functions.Func1;

public class GetFavoriteMoviesUseCase
    extends ContentResolverUseCase<Void, TMDBMovieDetailsResponse> {

    @Inject public GetFavoriteMoviesUseCase(
        @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
        @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
        ContentResolver contentResolver) {
        super(executionScheduler, postExecutionScheduler, contentResolver);
    }

    @Override
    protected Observable<TMDBMovieDetailsResponse> buildUseCaseObservable(Void parameter) {
        return Observable.defer(new Func0<Observable<Cursor>>() {
            @Override public Observable<Cursor> call() {
                Cursor cursor =
                    contentResolver.query(PopMovDBContract.FavMovie.CONTENT_URI, null, null, null,
                        null);

                return Observable.just(cursor);
            }
        }).flatMap(new Func1<Cursor, Observable<TMDBMovieDetailsResponse>>() {
            @Override public Observable<TMDBMovieDetailsResponse> call(Cursor cursor) {
                List<TMDBMovieDetailsResponse> movies = new ArrayList<TMDBMovieDetailsResponse>();

                while (cursor.moveToNext()) {
                    TMDBMovieDetailsResponse movie = new TMDBMovieDetailsResponse();
                    movie.setId(cursor.getInt(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_MOVIE_ID)));
                    movie.setTitle(cursor.getString(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_TITLE)));
                    movie.setPoster(cursor.getString(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_POSTER)));
                    movie.setOverview(cursor.getString(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_OVERVIEW)));
                    movie.setDuration(cursor.getInt(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_DURATION)));
                    movie.setRating(cursor.getFloat(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_RATING)));
                    movie.setReleaseDate(cursor.getString(
                        cursor.getColumnIndex(PopMovDBContract.FavMovie.COL_RELEASE_DATE)));
                    movies.add(movie);
                }

                cursor.close();
                return Observable.from(movies);
            }
        });
    }
}
