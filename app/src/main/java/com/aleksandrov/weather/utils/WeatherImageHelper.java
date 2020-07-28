package com.aleksandrov.weather.utils;

import androidx.annotation.DrawableRes;

import com.aleksandrov.weather.R;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

@Singleton
public class WeatherImageHelper {

    private static Map<String, Integer> mMap;

    static {
        mMap = new HashMap();
        mMap.put("Snow", R.drawable.sn);
        mMap.put("Sleet", R.drawable.sl);
        mMap.put("Hail", R.drawable.h);
        mMap.put("Thunderstorm", R.drawable.t);
        mMap.put("Heavy Rain", R.drawable.hr);
        mMap.put("Light Rain", R.drawable.lr);
        mMap.put("Showers", R.drawable.s);
        mMap.put("Heavy Cloud", R.drawable.hc);
        mMap.put("Light Cloud", R.drawable.lc);
        mMap.put("Clear", R.drawable.c);
    }

    public static @DrawableRes
    int getDrawableResByName(String name) {
        return mMap.get(name);
    }

}
