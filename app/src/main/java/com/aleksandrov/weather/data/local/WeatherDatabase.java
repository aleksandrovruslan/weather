package com.aleksandrov.weather.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aleksandrov.weather.data.entities.BaseLocation;

@Database(entities = {BaseLocation.class}, version = 1, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract LocationDao locationDao();

}
