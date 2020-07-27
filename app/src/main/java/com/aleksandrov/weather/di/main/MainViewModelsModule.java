package com.aleksandrov.weather.di.main;

import androidx.lifecycle.ViewModel;

import com.aleksandrov.weather.di.ViewModelKey;
import com.aleksandrov.weather.presentation.viewmodel.append.AppendViewModel;
import com.aleksandrov.weather.presentation.viewmodel.home.HomeViewModel;
import com.aleksandrov.weather.presentation.viewmodel.settings.SettingsViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelsModule {

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    public static ViewModel provideHomeViewModel() {
        return new HomeViewModel();
    }

    @Binds
    @IntoMap
    @ViewModelKey(AppendViewModel.class)
    public abstract ViewModel bindAppendViewModel(AppendViewModel viewModel);

    @Provides
    @IntoMap
    @ViewModelKey(SettingsViewModel.class)
    public static ViewModel provideSettingsViewModel() {
        return new SettingsViewModel();
    }

}
