package com.example.tanjinc.myapplication.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.observable.ObservableAll;
import io.reactivex.internal.operators.observable.ObservableAny;
import io.reactivex.schedulers.Schedulers;

public class MyViewModel extends ViewModel {
    private static final String TAG = "MyViewModel";

    private Observable<List<String>> data;

    public Observable<List<String>> getData() {
        Log.d(TAG, "getData: ");
        if (data == null) {
            data = new Observable<List<String>>() {
                @Override
                protected void subscribeActual(Observer<? super List<String>> observer) {

                }
            };
        }
        return data;
    }

    @SuppressLint("CheckResult")
    private void loadData() {

        data.observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<String>>() {
                @Override
                public void accept(List<String> strings) throws Exception {
                    Log.d(TAG, "accept: " + strings);
                }
            });
    }

    @Override
    protected void onCleared() {
        Log.d(TAG, "onCleared: ");
        super.onCleared();
    }
}
