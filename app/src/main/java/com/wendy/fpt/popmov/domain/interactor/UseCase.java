package com.wendy.fpt.popmov.domain.interactor;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;

public abstract class UseCase<P, O> {

    protected final Scheduler executionScheduler, postExecutionScheduler;

    private Subscription subscription;

    public UseCase(Scheduler executionScheduler, Scheduler postExecutionScheduler) {
        this.executionScheduler = executionScheduler;
        this.postExecutionScheduler = postExecutionScheduler;
    }

    protected abstract Observable<O> buildUseCaseObservable(P parameter);

    public Observable<O> executionObservable(P parameter) {
        return this.buildUseCaseObservable(parameter)
                .subscribeOn(executionScheduler)
                .observeOn(postExecutionScheduler);
    }

    public void execute(Subscriber<O> useCaseSubscriber,
                        P parameter) {
        unsubscribe();
        this.subscription = executionObservable(parameter).subscribe(useCaseSubscriber);
    }

    public void unsubscribe() {
        if (subscription == null) return;
        subscription.unsubscribe();
    }

    public boolean isSubscribed() {
        return subscription != null && !subscription.isUnsubscribed();
    }

}
