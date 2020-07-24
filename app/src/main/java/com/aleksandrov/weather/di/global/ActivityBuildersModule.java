package com.aleksandrov.weather.di.global;

import com.aleksandrov.weather.MainActivity;
import com.aleksandrov.weather.di.MainScope;
import com.aleksandrov.weather.di.main.MainFragmentBuildersModule;
import com.aleksandrov.weather.di.main.MainViewModelsModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ActivityBuildersModule {

    @MainScope
    @ContributesAndroidInjector(modules = {
            MainFragmentBuildersModule.class,
            MainViewModelsModule.class
    })
    MainActivity contributeMainActivity();

}
