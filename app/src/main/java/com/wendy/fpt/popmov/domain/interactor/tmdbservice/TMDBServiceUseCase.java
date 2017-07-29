package com.wendy.fpt.popmov.domain.interactor.tmdbservice;

import com.wendy.fpt.popmov.domain.interactor.UseCase;
import com.wendy.fpt.popmov.service.TMDBService;

import rx.Scheduler;

public abstract class TMDBServiceUseCase<P, O> extends UseCase<P, O> {

    protected TMDBService tmdbService;

    public TMDBServiceUseCase(Scheduler executionScheduler, Scheduler postExecutionScheduler,
        TMDBService tmdbService) {
        super(executionScheduler, postExecutionScheduler);
        this.tmdbService = tmdbService;
    }
}
