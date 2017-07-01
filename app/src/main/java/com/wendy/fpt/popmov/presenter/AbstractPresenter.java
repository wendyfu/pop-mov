package com.wendy.fpt.popmov.presenter;

import com.wendy.fpt.popmov.domain.interactor.UseCase;

/**
 * Created by wendy on 01-Jul-17.
 */

public abstract class AbstractPresenter<V> {

    protected V view;

    protected abstract UseCase[] useCases();

    abstract void resume();

    abstract void pause();

    public void destroy() {
        UseCase[] useCases = useCases();
        for (UseCase useCase : useCases) {
            useCase.unsubscribe();
        }
    }

    public void setView(V view) {
        this.view = view;
    }
}
