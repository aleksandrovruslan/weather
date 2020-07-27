package com.aleksandrov.weather.data.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherApi {

    // api/location/search/?query=london
    @GET("location/search/?")
    Single<List<Location>> getLocationByName(@Query("query") String locationName);

    // api/location/search/?lattlong=50.068,-5.316
    @GET("location/search/?")
    Single<List<Location>> getLocationByLatitudeLongitude(@Query("lattlong") String lattlong);

    // api/location/2487956/ - San Francisco
    @GET("location/{woeid}/")
    Single<ConsolidatedWeatherResponse> getWeatherResponse(@Path("woeid") int woeid);

    // api/location/2487956/2013/4/30/ - San Francisco on 30th April 2013
    @GET("location/{woeid}/{year}/{mouth}/{day}/")
    Single<ConsolidatedWeatherResponse> getWeatherResponseByDate(@Path("woeid") int woeid
            , @Path("yaer") int year, @Path("mouth") int mouth, @Path("day") int day);

}
