package com.aleksandrov.weather.presentation.view.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.presentation.viewmodel.home.HomeViewModel;
import com.aleksandrov.weather.utils.ViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class HomeFragment extends DaggerFragment {

    @Inject
    ViewModelFactory mFactory;

    private HomeViewModel mHomeViewModel;
    private ProgressBar mProgressBar;
    private TextView mHomeMessage;
    private LocationsAdapter mAdapter;
    private String mMessage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHomeViewModel = new ViewModelProvider(this, mFactory)
                .get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mProgressBar = root.findViewById(R.id.progress);
        mHomeMessage = root.findViewById(R.id.message);
        mMessage = getContext().getResources().getString(R.string.message_for_home_empty);
        RecyclerView recycler = root.findViewById(R.id.locations_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new LocationsAdapter(mHomeViewModel);
        recycler.setAdapter(mAdapter);
        FloatingActionButton fab = root.findViewById(R.id.append_fab);
        fab.setOnClickListener(view -> Navigation.findNavController(view)
                .navigate(R.id.action_nav_home_to_nav_append));
        observeViewModels();
        return root;
    }

    private void observeViewModels() {
        mHomeViewModel.getProgress().observe(getViewLifecycleOwner()
                , progress -> mProgressBar.setVisibility(
                        progress ? View.VISIBLE : View.INVISIBLE));
        mHomeViewModel.getLocations().observe(getViewLifecycleOwner()
                , baseLocations -> {
                    mHomeMessage.setText((baseLocations == null || baseLocations.size() < 1) ? mMessage : "");
                    mAdapter.addLocations(baseLocations);
                });
    }

}