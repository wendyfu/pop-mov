package com.wendy.fpt.popmov.data.exception;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.HttpException;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.functions.Func1;

public final class RxExceptionCallAdapterFactory extends CallAdapter.Factory {

    private final RxJavaCallAdapterFactory rxJavaCallAdapterFactory;

    private RxExceptionCallAdapterFactory() {
        rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    }

    public static CallAdapter.Factory create() {
        return new RxExceptionCallAdapterFactory();
    }

    @Override
    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new RxCallAdapter(retrofit,
            rxJavaCallAdapterFactory.get(returnType, annotations, retrofit));
    }

    private static class RxCallAdapter implements CallAdapter<Observable<?>> {

        private final Retrofit retrofit;
        private final CallAdapter<?> callAdapter;

        RxCallAdapter(Retrofit retrofit, CallAdapter<?> callAdapter) {
            this.retrofit = retrofit;
            this.callAdapter = callAdapter;
        }

        @Override public Type responseType() {
            return callAdapter.responseType();
        }

        @Override public <R> Observable<?> adapt(Call<R> call) {
            return ((Observable) callAdapter.adapt(call)).onErrorResumeNext(
                new Func1<Throwable, Observable>() {
                    @Override public Observable call(Throwable throwable) {
                        return Observable.error(errorRetrofitException(throwable));
                    }
                });
        }

        private RetrofitException errorRetrofitException(Throwable throwable) {
            if (throwable instanceof IOException) {
                return RetrofitException.networkError((IOException) throwable);
            } else if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) throwable;
                Response response = httpException.response();
                return RetrofitException.httpError(response.raw().request().url().toString(),
                    response, retrofit);
            }
            return RetrofitException.unexpectedError(throwable);
        }
    }
}
