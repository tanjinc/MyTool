package com.example.tanjinc.myapplication.view

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.viewmodel.MyViewModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MyActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        Log.d(TAG, "onCreate: ")

        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel.getData()
                ?.observeOn(Schedulers.io())
                ?.subscribeOn(AndroidSchedulers.mainThread())
                ?.subscribe { strings -> Log.d(TAG, "accept: $strings") }
    }


    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")
        super.onDestroy()
    }

    companion object {
        private val TAG = "MyActivity"
    }
}
