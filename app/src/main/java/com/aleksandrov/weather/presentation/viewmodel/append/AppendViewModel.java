package com.aleksandrov.weather.presentation.viewmodel.append;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aleksandrov.weather.data.entities.BaseLocation;
import com.aleksandrov.weather.data.remote.Location;
import com.aleksandrov.weather.data.repository.WeatherRepository;
import com.aleksandrov.weather.presentation.viewmodel.Event;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class AppendViewModel extends ViewModel {

    private WeatherRepository mRepository;
    private MutableLiveData<Boolean> mProgressLiveData;
    private MutableLiveData<List<Location>> mLocations;
    private MutableLiveData<Event<Object>> mEventLiveData;
    private BaseLocation mLocation;
    private CompositeDisposable mDisposable;

    @Inject
    public AppendViewModel(WeatherRepository repository) {
        mRepository = repository;
        mProgressLiveData = new MutableLiveData<>();
        mLocations = new MutableLiveData<>();
        mEventLiveData = new MutableLiveData<>();
        mDisposable = new CompositeDisposable();
    }

    public LiveData<Boolean> getProgress() {
        return mProgressLiveData;
    }

    public LiveData<List<Location>> getLocations() {
        return mLocations;
    }

    public void searchLocation(String locationName) {
        mProgressLiveData.setValue(true);
        mDisposable.add(mRepository.getLocationByName(locationName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(locations -> {
                            mLocations.setValue(locations);
                            mProgressLiveData.setValue(false);
                        }
                        , throwable -> {
                            mProgressLiveData.setValue(false);
                        }));
    }

    public LiveData<Event<Object>> getEvent() {
        return mEventLiveData;
    }

    public void insertLocation(Location location) {
        mLocation = new BaseLocation();
        mLocation.setTitle(location.getTitle());
        mLocation.setWoeid(location.getWoeid());
        mLocation.setLattLong(location.getLattLong());
        mLocation.setLocationType(location.getLocationType());
        mEventLiveData.setValue(new Event<>(new Object()));
    }

    public void saveLocation() {
        if (mLocation != null) {
            mProgressLiveData.setValue(true);
            mDisposable.add(mRepository.insertCityLocation(mLocation)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> mProgressLiveData.setValue(false)
                            , throwable -> mProgressLiveData.setValue(false)));
            mLocation = null;
        }
    }

    @Override
    protected void onCleared() {
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }

}