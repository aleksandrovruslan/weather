package com.aleksandrov.weather.presentation.view.details;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.aleksandrov.weather.data.remote.ConsolidatedWeather;

import java.util.Map;

public class PagerAdapter extends FragmentStateAdapter {

    private Map<Integer, ConsolidatedWeather> mWeatherMap;

    public PagerAdapter(@NonNull Fragment fragment
            , Map<Integer, ConsolidatedWeather> weatherMap) {
        super(fragment);
        mWeatherMap = weatherMap;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        ConsolidatedWeather cw = mWeatherMap.get(position);
        assert cw != null;
        return ConsolidatedWeatherFragment.newInstance(cw.getWeatherStateName()
                , String.format("Max: %.2f°C", cw.getMaxTemp())
                , String.format("Min: %.2f°C", cw.getMinTemp()));
    }

    @Override
    public int getItemCount() {
        return mWeatherMap.size();
    }

}
