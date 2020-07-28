package com.aleksandrov.weather.presentation.viewmodel.details;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.aleksandrov.weather.data.remote.ConsolidatedWeatherResponse;
import com.aleksandrov.weather.data.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {

    private WeatherRepository mRepository;
    private MutableLiveData<Boolean> mProgressLiveData;
    private MutableLiveData<ConsolidatedWeatherResponse> mWeatherResponseLiveData;
    private CompositeDisposable mDisposable;

    @Inject
    public DetailsViewModel(WeatherRepository repository) {
        mRepository = repository;
        mProgressLiveData = new MutableLiveData<>();
        mWeatherResponseLiveData = new MutableLiveData<>();
        mDisposable = new CompositeDisposable();
    }

    public LiveData<Boolean> getProgress() {
        return mProgressLiveData;
    }

    public LiveData<ConsolidatedWeatherResponse> getWeatherResponse() {
        return mWeatherResponseLiveData;
    }

    public void loadWeatherForWoeid(int woeid) {
        mProgressLiveData.setValue(true);
        Log.d("HomeViewModel", "loadWeatherForWoeid " + woeid);
        mDisposable.add(mRepository.getWeatherResponse(woeid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consolidatedWeatherResponse -> {
                            Log.d("HomeViewModel", "subscribe");
                            mWeatherResponseLiveData.setValue(consolidatedWeatherResponse);
                            mProgressLiveData.setValue(false);
                        }
                        , throwable -> {
                            mProgressLiveData.setValue(false);
                            Log.d("HomeViewModel", throwable.getMessage());
                        }));
    }

}
