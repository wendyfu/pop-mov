package com.wendy.fpt.popmov.domain;

import android.util.Log;

import rx.Subscriber;

public class DefaultSubscriber<T> extends Subscriber<T> {

    private final String TAG = getClass().getSimpleName();

    @Override public void onCompleted() {
        // do nothing by default.
    }

    @Override public void onError(Throwable e) {
        Log.e(TAG, "onError", e);
    }

    @Override public void onNext(T t) {
        // do nothing by default.
    }
}
