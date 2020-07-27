package com.aleksandrov.weather.data.repository;

import com.aleksandrov.weather.data.entities.BaseLocation;
import com.aleksandrov.weather.data.remote.ConsolidatedWeatherResponse;
import com.aleksandrov.weather.data.remote.Location;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


public interface WeatherRepository {

    Single<List<Location>> getLocationByName(String locationName);

    Single<List<Location>> getLocationByLatitudeLongitude(String lattlong);

    Single<ConsolidatedWeatherResponse> getWeatherResponse(int woeid);

    Single<ConsolidatedWeatherResponse> getWeatherResponseByDate(int woeid, int year, int mouth, int day);

    Completable insertCityLocation(BaseLocation location);

    Single<List<BaseLocation>> getAllCityLocation();

}
