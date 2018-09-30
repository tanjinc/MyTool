package com.example.tanjinc.myapplication.viewmodel

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log

import java.util.ArrayList

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.internal.operators.observable.ObservableAll
import io.reactivex.internal.operators.observable.ObservableAny
import io.reactivex.schedulers.Schedulers

class MyViewModel : ViewModel() {

    private var data: Observable<List<String>>? = null

    fun getData(): Observable<List<String>> ?{
        Log.d(TAG, "getData: ")
        if (data == null) {
            data = object : Observable<List<String>>() {
                override fun subscribeActual(observer: Observer<in List<String>>) {

                }
            }
        }
        return data
    }

    @SuppressLint("CheckResult")
    private fun loadData() {

        data!!.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe { strings -> Log.d(TAG, "accept: $strings") }
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: ")
        super.onCleared()
    }

    companion object {
        private val TAG = "MyViewModel"
    }
}
