package com.example.tanjinc.myapplication.fragment.gridpage;

import android.annotation.SuppressLint;

import com.example.tanjinc.myapplication.bean.ABean;
import com.example.tanjinc.myapplication.bean.BBean;
import com.example.tanjinc.myapplication.bean.BaseBean;
import com.example.tanjinc.myapplication.bean.SkinBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
@SuppressLint("CheckResult")
public class GridPresenter implements GridContract.Presenter {
    private static final String TAG = "GridPresenter";
    private GridContract.View mView;

    public GridPresenter(GridContract.View view) {
        mView = view;
    }

    @Override
    public void loadData() {
        Observable
                .create(new ObservableOnSubscribe<List<? extends BaseBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<? extends BaseBean>> emitter) throws Exception {
                        List<BaseBean> beanArrayList = new ArrayList<>();
                        for (int i = 0; i < 50; i++) {
                            int index = new Random().nextInt(3);
                            if (index == 0) {
                                beanArrayList.add(new SkinBean("index: " + i));
                            }
                            if (index == 1) {
                                beanArrayList.add(new ABean("AAA " + i));
                            }
                            if (index == 2) {
                                beanArrayList.add(new BBean("BBB " + i));
                            }
                        }


                        emitter.onNext(beanArrayList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .delay(2000, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<? extends BaseBean>>() {
                    @Override
                    public void accept(List<? extends BaseBean> s) throws Exception {
                        mView.showDataList(s, true);
                    }
                });
    }

    @Override
    public void loadMoreData() {
        Observable
                .create(new ObservableOnSubscribe<List<? extends BaseBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<? extends BaseBean>> emitter) throws Exception {
                        List<BaseBean> beanArrayList = new ArrayList<>();
                        for (int i = 0; i < 50; i++) {
                            int index = new Random().nextInt(3);
                            if (index == 0) {
                                beanArrayList.add(new SkinBean("index: " + i));
                            }
                            if (index == 1) {
                                beanArrayList.add(new ABean("AAA " + i));
                            }
                            if (index == 2) {
                                beanArrayList.add(new BBean("BBB " + i));
                            }
                        }


                        emitter.onNext(beanArrayList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<? extends BaseBean>>() {
                    @Override
                    public void accept(List<? extends BaseBean> s) throws Exception {
                        mView.showDataList(s, true);
                    }
                });
    }

    @Override
    public void refresh() {
        Observable
                .create(new ObservableOnSubscribe<List<? extends BaseBean>>() {
                    @Override
                    public void subscribe(ObservableEmitter<List<? extends BaseBean>> emitter) throws Exception {
                        List<BaseBean> beanArrayList = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            int index = new Random().nextInt(3);
                            if (index == 0) {
                                beanArrayList.add(new SkinBean("index: " + i));
                            }
                            if (index == 1) {
                                beanArrayList.add(new ABean("AAA " + i));
                            }
                            if (index == 2) {
                                beanArrayList.add(new BBean("BBB " + i));
                            }
                        }


                        emitter.onNext(beanArrayList);
                    }
                })
                .subscribeOn(Schedulers.io())
                .delay(2000, TimeUnit.MICROSECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<? extends BaseBean>>() {
                    @Override
                    public void accept(List<? extends BaseBean> s) throws Exception {
                        mView.showDataList(s, false);
                    }
                });
    }
}
