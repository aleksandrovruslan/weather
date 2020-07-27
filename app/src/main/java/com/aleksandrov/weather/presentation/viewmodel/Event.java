package com.aleksandrov.weather.presentation.viewmodel;

public class Event<T> {

    private T mContent;
    private boolean mHasBeenHandled;

    public Event(T content) {
        if (content == null) {
            throw new IllegalArgumentException("Null values in Event are not allowed.");
        }
        mContent = content;
    }

    public synchronized T getContentIfNotHandled() {
        if (mHasBeenHandled) {
            return null;
        } else {
            mHasBeenHandled = true;
            return mContent;
        }
    }

}
