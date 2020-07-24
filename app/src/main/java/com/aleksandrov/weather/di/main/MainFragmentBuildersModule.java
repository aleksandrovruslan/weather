package com.aleksandrov.weather.di.main;

import com.aleksandrov.weather.presentation.view.append.AppendFragment;
import com.aleksandrov.weather.presentation.view.home.HomeFragment;
import com.aleksandrov.weather.presentation.view.settings.SettingsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MainFragmentBuildersModule {

    @ContributesAndroidInjector
    HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    AppendFragment contributeAppendCityFragment();

    @ContributesAndroidInjector
    SettingsFragment contributeSettingsFragment();

}
