package com.aleksandrov.weather.presentation.view.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aleksandrov.weather.R;
import com.aleksandrov.weather.data.entities.BaseLocation;
import com.aleksandrov.weather.presentation.viewmodel.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationViewHolder> {

    private List<BaseLocation> mLocations = new ArrayList<>();
    private HomeViewModel mViewModel;

    public LocationsAdapter(HomeViewModel viewModel) {
        mViewModel = viewModel;
    }

    public void addLocations(List<BaseLocation> locations) {
        mLocations.clear();
        mLocations.addAll(locations);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        BaseLocation location = mLocations.get(position);
        holder.onBind(location, v -> mViewModel.detailsForWoeid(location.getWoeid()));
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    class LocationViewHolder extends RecyclerView.ViewHolder {

        private TextView mLocationName;
        private TextView mLocationLatLong;
        private View mLayout;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);

            mLocationName = itemView.findViewById(R.id.location_name);
            mLocationLatLong = itemView.findViewById(R.id.location_latlong);
            mLayout = itemView.findViewById(R.id.home_item_layout);
        }

        public void onBind(BaseLocation location, View.OnClickListener listener) {
            mLocationName.setText(location.getTitle());
            mLocationLatLong.setText(location.getLattLong());
            mLayout.setOnClickListener(listener);
        }

    }

}
