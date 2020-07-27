package com.aleksandrov.weather.presentation.view.append;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.data.remote.Location;
import com.aleksandrov.weather.presentation.viewmodel.append.AppendViewModel;

import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<Location> mLocations = new ArrayList<>();
    private AppendViewModel mAppendViewModel;

    public LocationAdapter(AppendViewModel appendViewModel) {
        mAppendViewModel = appendViewModel;
    }

    public void addLocations(List<Location> locations) {
        mLocations.clear();
        mLocations.addAll(locations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        holder.onBind(mLocations.get(position)
                , v -> mAppendViewModel.insertLocation(mLocations.get(position)));
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        private TextView mLocationName;
        private ImageButton mAddButton;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            mLocationName = itemView.findViewById(R.id.location_name);
            mAddButton = itemView.findViewById(R.id.add_location_btn);
        }

        public void onBind(Location location, View.OnClickListener listener) {
            mLocationName.setText(location.getTitle());
            mAddButton.setOnClickListener(listener);
        }

    }

}
