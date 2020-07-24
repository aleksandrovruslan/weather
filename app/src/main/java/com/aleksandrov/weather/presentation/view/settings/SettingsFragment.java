package com.aleksandrov.weather.presentation.view.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.presentation.viewmodel.settings.SettingsViewModel;
import com.aleksandrov.weather.utils.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class SettingsFragment extends DaggerFragment {

    @Inject
    ViewModelFactory mFactory;

    private SettingsViewModel mSettingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mSettingsViewModel = new ViewModelProvider(this, mFactory)
                .get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        mSettingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}