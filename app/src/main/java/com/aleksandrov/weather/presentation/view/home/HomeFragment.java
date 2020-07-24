package com.aleksandrov.weather.presentation.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.presentation.viewmodel.home.HomeViewModel;
import com.aleksandrov.weather.utils.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    @Inject
    ViewModelFactory mFactory;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this, mFactory)
                .get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        FloatingActionButton fab = root.findViewById(R.id.append_fab);
        fab.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_nav_home_to_nav_append));
        return root;
    }

}