package com.wendy.fpt.popmov.di;

import com.wendy.fpt.popmov.PopMovApplication;
import com.wendy.fpt.popmov.di.module.PopMovAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {PopMovAppModule.class})
@Singleton public interface PopMovApplicationComponent {

    void inject(PopMovApplication popMovApplication);
}
