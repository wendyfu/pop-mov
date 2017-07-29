package com.wendy.fpt.popmov;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.wendy.fpt.popmov.di.DaggerPopMovApplicationComponent;
import com.wendy.fpt.popmov.di.PopMovApplicationComponent;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;

public class PopMovApplication extends Application {

    private static PopMovApplication currentApplication;

    private PopMovApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        currentApplication = this;
        appComponent = DaggerPopMovApplicationComponent.builder()
                .popMovAppModule(new PopMovAppModule(this))
                .build();
        appComponent.inject(this);

        Stetho.initializeWithDefaults(this);
    }

    public static PopMovApplication getCurrentApplication() {
        return currentApplication;
    }

    public PopMovApplicationComponent getAppComponent() {
        return appComponent;
    }

}
