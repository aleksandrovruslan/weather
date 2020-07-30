package com.aleksandrov.weather.presentation.view.append;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.presentation.viewmodel.append.AppendViewModel;
import com.aleksandrov.weather.utils.ViewModelFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AppendFragment extends DaggerFragment implements AppendDialogFragment.AppendDialogListener {

    @Inject
    ViewModelFactory mFactory;

    private AppendViewModel mAppendViewModel;
    private ProgressBar mProgressBar;
    private LocationsAdapter mAdapter;
    private View mAddLayout;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAppendViewModel = new ViewModelProvider(this, mFactory)
                .get(AppendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_append, container, false);
        mAddLayout = root.findViewById(R.id.add_layout);
        mProgressBar = root.findViewById(R.id.progress);
        final RecyclerView recycler = root.findViewById(R.id.recycler_locations);
        mAdapter = new LocationsAdapter(mAppendViewModel);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(mAdapter);
        EditText editLocationName = root.findViewById(R.id.edit_location_name);
        editLocationName.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                String locationName = textView.getText().toString().trim();
                if (locationName.length() > 0) {
                    mAppendViewModel.searchLocation(locationName);
                }
                return true;
            }
            return false;
        });
        observeViewModels();
        return root;
    }

    private void observeViewModels() {
        mAppendViewModel.getProgress().observe(getViewLifecycleOwner(),
                progress -> mProgressBar.setVisibility(
                        progress ? View.VISIBLE : View.INVISIBLE));
        mAppendViewModel.getLocations().observe(getViewLifecycleOwner(), mAdapter::addLocations);
        mAppendViewModel.getEvent().observe(getViewLifecycleOwner(), event -> {
            if (event.getContentIfNotHandled() != null) {
                new AppendDialogFragment().show(getChildFragmentManager(), AppendDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        mAppendViewModel.saveLocation();
        Snackbar.make(mAddLayout, R.string.add_location_message
                , Snackbar.LENGTH_LONG)
                .show();
        dialog.dismiss();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        dialog.dismiss();
    }

}