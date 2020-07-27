package com.aleksandrov.weather.di.global;

import android.app.Application;

import androidx.room.Room;

import com.aleksandrov.weather.data.local.LocationDao;
import com.aleksandrov.weather.data.local.WeatherDatabase;
import com.aleksandrov.weather.data.remote.CacheInterceptor;
import com.aleksandrov.weather.data.remote.WeatherApi;
import com.aleksandrov.weather.data.repository.WeatherRepository;
import com.aleksandrov.weather.data.repository.WeatherRepositoryImpl;

import java.io.File;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class RepositoryModule {

    private static final String BASE_URL = "https://www.metaweather.com/api/";

    @Singleton
    @Provides
    public static OkHttpClient provideOkHttpClient(Application application, CacheInterceptor interceptor) {
        Cache cache = new Cache(
                new File(application.getCacheDir(), "http_cache")
                , 10L * 1024L * 1024L);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .cache(cache)
                .build();
    }

    @Singleton
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    public static WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Singleton
    @Binds
    public abstract WeatherRepository bindWeatherRepository(WeatherRepositoryImpl repository);

    @Singleton
    @Provides
    public static WeatherDatabase provideWeatherDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext()
                , WeatherDatabase.class, "weather_database").build();
    }

    @Singleton
    @Provides
    public static LocationDao provideLocationDao(WeatherDatabase database) {
        return database.locationDao();
    }

    @Singleton
    @Provides
    public static CacheInterceptor provideCacheInterceptor() {
        return new CacheInterceptor();
    }

}
