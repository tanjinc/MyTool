package com.example.tanjinc.myapplication.fragment.testpage;

import com.example.tanjinc.myapplication.bean.TestBean;

import java.util.ArrayList;

public class TestPresenter implements TestContract.Presenter {
    private TestContract.View mView;

    public TestPresenter(TestContract.View view) {
        mView = view;
    }

    @Override
    public void loadTest() {
        ArrayList<TestBean> beanArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            beanArrayList.add(new TestBean("index: " + i));
        }
        mView.showTestList(beanArrayList, false);
    }

    @Override
    public void loadMoreTest() {
        ArrayList<TestBean> beanArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            beanArrayList.add(new TestBean("more: " + i));
        }
        mView.showTestList(beanArrayList, true);
    }

    @Override
    public void refresh() {
        ArrayList<TestBean> beanArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            beanArrayList.add(new TestBean("refresh: " + i));
        }
        mView.showTestList(beanArrayList, false);
    }
}
