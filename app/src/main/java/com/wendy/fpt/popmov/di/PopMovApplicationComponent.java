package com.wendy.fpt.popmov.di;

import com.wendy.fpt.popmov.PopMovApplication;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;
import com.wendy.fpt.popmov.view.activity.MainFragment;
import com.wendy.fpt.popmov.view.activity.MovieDetailFragment;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {PopMovAppModule.class})
@Singleton public interface PopMovApplicationComponent {

    void inject(PopMovApplication popMovApplication);

    void inject(MainFragment mainFragment);

    void inject(MovieDetailFragment movieDetailFragment);
}
