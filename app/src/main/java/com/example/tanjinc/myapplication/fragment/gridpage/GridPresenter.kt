package com.example.tanjinc.myapplication.fragment.gridpage

import android.annotation.SuppressLint

import com.example.tanjinc.myapplication.bean.ABean
import com.example.tanjinc.myapplication.bean.BBean
import com.example.tanjinc.myapplication.bean.BaseBean
import com.example.tanjinc.myapplication.bean.SkinBean

import java.util.ArrayList
import java.util.Random
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult")
class GridPresenter(private val mView: GridContract.View) : GridContract.Presenter {

    override fun loadData() {
        Observable
                .create(ObservableOnSubscribe<List<BaseBean>> { emitter ->
                    val beanArrayList = ArrayList<BaseBean>()
                    for (i in 0..49) {
                        val index = Random().nextInt(3)
                        if (index == 0) {
                            beanArrayList.add(SkinBean("index: $i"))
                        }
                        if (index == 1) {
                            beanArrayList.add(ABean("AAA $i"))
                        }
                        if (index == 2) {
                            beanArrayList.add(BBean("BBB $i"))
                        }
                    }


                    emitter.onNext(beanArrayList)
                })
                .subscribeOn(Schedulers.io())
                .delay(2000, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s -> mView.showDataList(s, true) }
    }

    override fun loadMoreData() {
        Observable
                .create(ObservableOnSubscribe<List<BaseBean>> { emitter ->
                    val beanArrayList = ArrayList<BaseBean>()
                    for (i in 0..49) {
                        val index = Random().nextInt(3)
                        if (index == 0) {
                            beanArrayList.add(SkinBean("index: $i"))
                        }
                        if (index == 1) {
                            beanArrayList.add(ABean("AAA $i"))
                        }
                        if (index == 2) {
                            beanArrayList.add(BBean("BBB $i"))
                        }
                    }


                    emitter.onNext(beanArrayList)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s -> mView.showDataList(s, true) }
    }

    override fun refresh() {
        Observable
                .create(ObservableOnSubscribe<List<BaseBean>> { emitter ->
                    val beanArrayList = ArrayList<BaseBean>()
                    for (i in 0..19) {
                        val index = Random().nextInt(3)
                        if (index == 0) {
                            beanArrayList.add(SkinBean("index: $i"))
                        }
                        if (index == 1) {
                            beanArrayList.add(ABean("AAA $i"))
                        }
                        if (index == 2) {
                            beanArrayList.add(BBean("BBB $i"))
                        }
                    }


                    emitter.onNext(beanArrayList)
                })
                .subscribeOn(Schedulers.io())
                .delay(2000, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { s -> mView.showDataList(s, false) }
    }

    companion object {
        private val TAG = "GridPresenter"
    }
}
