package com.aleksandrov.weather.di.main;

import androidx.lifecycle.ViewModel;

import com.aleksandrov.weather.di.ViewModelKey;
import com.aleksandrov.weather.presentation.viewmodel.append.AppendViewModel;
import com.aleksandrov.weather.presentation.viewmodel.home.HomeViewModel;
import com.aleksandrov.weather.presentation.viewmodel.settings.SettingsViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class MainViewModelsModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public ViewModel provideHomeViewModel() {
        return new HomeViewModel();
    }

    @Provides
    @IntoMap
    @ViewModelKey(AppendViewModel.class)
    public ViewModel provideAppendViewModel() {
        return new AppendViewModel();
    }

    @Provides
    @IntoMap
    @ViewModelKey(SettingsViewModel.class)
    public ViewModel provideSettingsViewModel() {
        return new SettingsViewModel();
    }

}
