package com.aleksandrov.weather.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.aleksandrov.weather.data.entities.BaseLocation;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locations")
    Single<List<BaseLocation>> getAllCityLocation();

    @Insert
    Completable insertCityLocation(BaseLocation... baseLocation);

    @Delete
    Completable deleteCityLocation(BaseLocation baseLocation);

}
