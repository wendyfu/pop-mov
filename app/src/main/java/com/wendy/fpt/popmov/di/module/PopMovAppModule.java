package com.wendy.fpt.popmov.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wendy.fpt.popmov.BuildConfig;
import com.wendy.fpt.popmov.PopMovApplication;
import com.wendy.fpt.popmov.data.deserializer.TMDBMoviesResponseDeserializer;
import com.wendy.fpt.popmov.data.exception.RxExceptionCallAdapterFactory;
import com.wendy.fpt.popmov.data.model.TMDBMoviesResponse;
import com.wendy.fpt.popmov.service.TMDBService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module public class PopMovAppModule {

    public static final String NAME_SCHEDULER_IO = "schedulerIo";
    public static final String NAME_UI_THREAD = "uiThread";

    private Context appContext;

    public PopMovAppModule(Context popMovAppContext) {
        this.appContext = popMovAppContext;
    }

    @Provides @Singleton public Context provideApplicationContext() {
        return appContext;
    }

    @Provides @Singleton public TMDBService provideTMDBService() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(TMDBMoviesResponse.class, new TMDBMoviesResponseDeserializer())
                .create();

        Retrofit retrofit =
                new Retrofit.Builder().baseUrl(BuildConfig.TMDB_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxExceptionCallAdapterFactory.create()).build();

        return retrofit.create(TMDBService.class);
    }

    @Provides @Named(NAME_SCHEDULER_IO) public Scheduler provideSchedulerIo() {
        return Schedulers.io();
    }

    @Provides @Named(NAME_UI_THREAD) public Scheduler provideUiThread() {
        return AndroidSchedulers.mainThread();
    }
}
