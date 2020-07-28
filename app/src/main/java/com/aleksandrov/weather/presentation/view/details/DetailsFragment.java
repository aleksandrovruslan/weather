package com.aleksandrov.weather.presentation.view.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.data.remote.ConsolidatedWeather;
import com.aleksandrov.weather.presentation.viewmodel.details.DetailsViewModel;
import com.aleksandrov.weather.utils.ViewModelFactory;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class DetailsFragment extends DaggerFragment {

    @Inject
    ViewModelFactory mFactory;

    private DetailsViewModel mDetailsViewModel;
    private ProgressBar mProgressBar;
    private TextView mLocationName;
    private TextView mSunRise;
    private TextView mSunSet;
    private ViewPager2 mViewPager;
    private TabLayout mTabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDetailsViewModel = new ViewModelProvider(this, mFactory)
                .get(DetailsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_details, container, false);
        mProgressBar = root.findViewById(R.id.progress);
        mLocationName = root.findViewById(R.id.location_name);
        mSunRise = root.findViewById(R.id.sun_rise);
        mSunSet = root.findViewById(R.id.sun_set);
        mViewPager = root.findViewById(R.id.view_pager);
        mTabLayout = root.findViewById(R.id.tab_layout);
        observeViewModels();
        int woeid = DetailsFragmentArgs.fromBundle(getArguments()).getWoeid();
        mDetailsViewModel.loadWeatherForWoeid(woeid);
        return root;
    }

    private void observeViewModels() {
        mDetailsViewModel.getProgress().observe(getViewLifecycleOwner()
                , progress -> mProgressBar.setVisibility(
                        progress ? View.VISIBLE : View.INVISIBLE));
        mDetailsViewModel.getWeatherResponse().observe(getViewLifecycleOwner(), response -> {
            Map<Integer, ConsolidatedWeather> weatherMap = new HashMap<>();
            int value = 0;
            for (ConsolidatedWeather cw : response.getConsolidatedWeather()) {
                weatherMap.put(value++, cw);
            }
            mViewPager.setAdapter(new PagerAdapter(this, weatherMap));
            new TabLayoutMediator(mTabLayout, mViewPager
                    , (tab, position) -> tab.setText(
                    response.getConsolidatedWeather().get(position).getApplicableDate()))
                    .attach();
            mLocationName.setText(response.getTitle());
            mSunRise.setText(String.format("Sunrise: %s", response.getSunRise()));
            mSunSet.setText(String.format("Sunset: %s", response.getSunSet()));
        });
    }

}