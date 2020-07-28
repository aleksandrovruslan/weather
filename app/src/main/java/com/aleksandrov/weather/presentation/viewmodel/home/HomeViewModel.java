package com.aleksandrov.weather.presentation.viewmodel.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aleksandrov.weather.data.entities.BaseLocation;
import com.aleksandrov.weather.data.repository.WeatherRepository;
import com.aleksandrov.weather.presentation.viewmodel.Event;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomeViewModel extends ViewModel {

    private WeatherRepository mRepository;
    private MutableLiveData<List<BaseLocation>> mLocationsLiveData;
    private MutableLiveData<Boolean> mProgressLiveData;
    private MutableLiveData<Event<Integer>> mDetailsWoeidLiveData;
    private CompositeDisposable mDisposable;

    @Inject
    public HomeViewModel(WeatherRepository repository) {
        mRepository = repository;
        mLocationsLiveData = new MutableLiveData<>();
        mProgressLiveData = new MutableLiveData<>();
        mDetailsWoeidLiveData = new MutableLiveData<>();
        mDisposable = new CompositeDisposable();
        loadLocations();
    }

    public LiveData<List<BaseLocation>> getLocations() {
        return mLocationsLiveData;
    }

    public LiveData<Boolean> getProgress() {
        return mProgressLiveData;
    }

    public LiveData<Event<Integer>> getDetailsWoeid() {
        return mDetailsWoeidLiveData;
    }

    public void detailsForWoeid(int woeid) {
        mDetailsWoeidLiveData.setValue(new Event<>(woeid));
    }

    private void loadLocations() {
        mProgressLiveData.setValue(true);
        mDisposable.add(mRepository.getAllCityLocation()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseLocations -> {
                    mLocationsLiveData.setValue(baseLocations);
                    mProgressLiveData.setValue(false);
                }, throwable -> {
                    mProgressLiveData.setValue(false);
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mDisposable != null) {
            mDisposable.clear();
            mDisposable = null;
        }
    }

}