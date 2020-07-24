package com.aleksandrov.weather.presentation.view.append;

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
import com.aleksandrov.weather.presentation.viewmodel.append.AppendViewModel;
import com.aleksandrov.weather.utils.ViewModelFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class AppendFragment extends DaggerFragment {

    @Inject
    ViewModelFactory mFactory;

    private AppendViewModel mAppendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mAppendViewModel = new ViewModelProvider(this, mFactory)
                .get(AppendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        mAppendViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}