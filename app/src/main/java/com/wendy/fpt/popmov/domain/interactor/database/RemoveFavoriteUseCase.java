package com.wendy.fpt.popmov.domain.interactor.database;

import android.content.ContentResolver;
import android.net.Uri;

import com.wendy.fpt.popmov.data.contract.PopMovDBContract;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func0;

public class RemoveFavoriteUseCase extends ContentResolverUseCase<Integer, Boolean> {

    @Inject public RemoveFavoriteUseCase(
        @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
        @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
        ContentResolver contentResolver) {
        super(executionScheduler, postExecutionScheduler, contentResolver);
    }

    @Override protected Observable<Boolean> buildUseCaseObservable(final Integer movieId) {
        return Observable.defer(new Func0<Observable<Boolean>>() {
            @Override public Observable<Boolean> call() {
                Uri deleteUri = PopMovDBContract.FavMovie.CONTENT_URI.buildUpon()
                    .appendPath(String.valueOf(movieId))
                    .build();
                int deletedRow =
                    contentResolver.delete(deleteUri, null, null);
                return Observable.just(deletedRow > 0);
            }
        });
    }
}
