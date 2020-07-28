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
    private View mRoot;
    private ProgressBar mProgressBar;
    private TextView mHomeMessage;
    private LocationsAdapter mAdapter;
    private String mMessage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mHomeViewModel = new ViewModelProvider(this, mFactory)
                .get(HomeViewModel.class);
        mRoot = inflater.inflate(R.layout.fragment_home, container, false);
        mProgressBar = mRoot.findViewById(R.id.progress);
        mHomeMessage = mRoot.findViewById(R.id.message);
        mMessage = getContext().getResources().getString(R.string.message_for_home_empty);
        RecyclerView recycler = mRoot.findViewById(R.id.locations_recycler);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new LocationsAdapter(mHomeViewModel);
        recycler.setAdapter(mAdapter);
        FloatingActionButton fab = mRoot.findViewById(R.id.append_fab);
        fab.setOnClickListener(view -> Navigation.findNavController(view)
                .navigate(R.id.action_nav_home_to_nav_append));
        observeViewModels();
        return mRoot;
    }

    private void observeViewModels() {
        mHomeViewModel.getProgress().observe(getViewLifecycleOwner()
                , progress -> mProgressBar.setVisibility(
                        progress ? View.VISIBLE : View.INVISIBLE));
        mHomeViewModel.getLocations().observe(getViewLifecycleOwner()
                , baseLocations -> {
                    mHomeMessage.setText((baseLocations == null ||
                            baseLocations.size() < 1) ? mMessage : "");
                    mAdapter.addLocations(baseLocations);
                });
        mHomeViewModel.getDetailsWoeid().observe(getViewLifecycleOwner(), woeidEvent -> {
            Integer woeid = woeidEvent.getContentIfNotHandled();
            if (woeid != null) {
                HomeFragmentDirections.ActionNavHomeToDetailsFragment action =
                        HomeFragmentDirections.actionNavHomeToDetailsFragment();
                action.setWoeid(woeid);
                Navigation.findNavController(mRoot)
                        .navigate(action);
            }
        });
    }

}