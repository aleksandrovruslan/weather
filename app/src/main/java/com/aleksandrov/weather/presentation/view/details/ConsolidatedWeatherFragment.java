package com.aleksandrov.weather.presentation.view.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.utils.WeatherImageHelper;

public class ConsolidatedWeatherFragment extends Fragment {

    private static final String ARG_WEATHER_STATE_NAME = "ARG_WEATHER_STATE_NAME";
    private static final String ARG_MAX_TEMP = "ARG_MAX_TEMP";
    private static final String ARG_MIN_TEMP = "ARG_MIN_TEMP";

    private String mWeatherStateName;
    private String mMaxTemp;
    private String mMinTemp;
    private ImageView mWeatherImg;

    public ConsolidatedWeatherFragment() {
        // Required empty public constructor
    }

    public static ConsolidatedWeatherFragment newInstance(
            String weatherStateName, String maxTemp, String minTemp) {
        ConsolidatedWeatherFragment fragment = new ConsolidatedWeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WEATHER_STATE_NAME, weatherStateName);
        args.putString(ARG_MAX_TEMP, maxTemp);
        args.putString(ARG_MIN_TEMP, minTemp);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWeatherStateName = getArguments().getString(ARG_WEATHER_STATE_NAME);
            mMaxTemp = getArguments().getString(ARG_MAX_TEMP);
            mMinTemp = getArguments().getString(ARG_MIN_TEMP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_consolidated_weather
                , container, false);
        TextView weatherStateName = root.findViewById(R.id.weather_state_name);
        weatherStateName.setText(mWeatherStateName);
        TextView maxTemp = root.findViewById(R.id.max_temp);
        maxTemp.setText(mMaxTemp);
        TextView minTemp = root.findViewById(R.id.min_temp);
        minTemp.setText(mMinTemp);
        mWeatherImg = root.findViewById(R.id.weather_img);
        mWeatherImg.setImageDrawable(getContext()
                .getDrawable(WeatherImageHelper
                        .getDrawableResByName(mWeatherStateName)));
        return root;
    }

}