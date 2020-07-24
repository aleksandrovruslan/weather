package com.aleksandrov.weather.di.global;

import androidx.lifecycle.ViewModelProvider;

import com.aleksandrov.weather.utils.ViewModelFactory;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Singleton
    @Binds
    public abstract ViewModelProvider.Factory bindFactory(ViewModelFactory factory);

}
