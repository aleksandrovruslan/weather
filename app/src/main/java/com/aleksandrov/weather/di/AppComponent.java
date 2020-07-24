package com.aleksandrov.weather.di;

import android.app.Application;

import com.aleksandrov.weather.WeatherApp;
import com.aleksandrov.weather.di.global.ActivityBuildersModule;
import com.aleksandrov.weather.di.global.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuildersModule.class
})
public interface AppComponent extends AndroidInjector<WeatherApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

}
