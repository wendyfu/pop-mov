package com.wendy.fpt.popmov.domain.interactor.database;

import android.content.ContentResolver;

import com.wendy.fpt.popmov.domain.interactor.UseCase;

import rx.Scheduler;

public abstract class ContentResolverUseCase<P, O> extends UseCase<P, O> {

    protected ContentResolver contentResolver;

    public ContentResolverUseCase(Scheduler executionScheduler, Scheduler postExecutionScheduler,
        ContentResolver contentResolver) {
        super(executionScheduler, postExecutionScheduler);
        this.contentResolver = contentResolver;
    }
}
