package com.wendy.fpt.popmov.domain.interactor;

import com.wendy.fpt.popmov.di.module.PopMovAppModule;
import com.wendy.fpt.popmov.service.TMDBService;

import javax.inject.Named;

import rx.Scheduler;

public abstract class TMDBServiceUseCase<P, O> extends UseCase<P, O> {

    protected TMDBService tmdbService;

    public TMDBServiceUseCase(
            @Named(PopMovAppModule.NAME_SCHEDULER_IO) Scheduler executionScheduler,
            @Named(PopMovAppModule.NAME_UI_THREAD) Scheduler postExecutionScheduler,
            TMDBService tmdbService) {
        super(executionScheduler, postExecutionScheduler);
        this.tmdbService = tmdbService;
    }
}
