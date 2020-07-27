package com.aleksandrov.weather.data.repository;

import com.aleksandrov.weather.data.entities.BaseLocation;
import com.aleksandrov.weather.data.local.LocationDao;
import com.aleksandrov.weather.data.remote.ConsolidatedWeatherResponse;
import com.aleksandrov.weather.data.remote.Location;
import com.aleksandrov.weather.data.remote.WeatherApi;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class WeatherRepositoryImpl implements WeatherRepository {

    private WeatherApi mApi;
    private LocationDao mLocationDao;

    @Inject
    public WeatherRepositoryImpl(WeatherApi api, LocationDao locationDao) {
        mApi = api;
        mLocationDao = locationDao;
    }

    @Override
    public Single<List<Location>> getLocationByName(String locationName) {
        return mApi.getLocationByName(locationName);
    }

    @Override
    public Single<List<Location>> getLocationByLatitudeLongitude(String lattlong) {
        return mApi.getLocationByLatitudeLongitude(lattlong);
    }

    @Override
    public Single<ConsolidatedWeatherResponse> getWeatherResponse(int woeid) {
        return mApi.getWeatherResponse(woeid);
    }

    @Override
    public Single<ConsolidatedWeatherResponse> getWeatherResponseByDate(int woeid, int year, int mouth, int day) {
        return mApi.getWeatherResponseByDate(woeid, year, mouth, day);
    }

    @Override
    public Completable insertCityLocation(BaseLocation location) {
        return mLocationDao.insertCityLocation(location);
    }

    @Override
    public Single<List<BaseLocation>> getAllCityLocation() {
        return mLocationDao.getAllCityLocation();
    }

}
