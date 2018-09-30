package com.example.tanjinc.myapplication.fragment.testpage

import com.example.tanjinc.myapplication.bean.TestBean

import java.util.ArrayList

class TestPresenter(private val mView: TestContract.View) : TestContract.Presenter {

    override fun loadTest() {
        val beanArrayList = ArrayList<TestBean>()
        for (i in 0..9) {
            beanArrayList.add(TestBean("index: $i"))
        }
        mView.showTestList(beanArrayList, false)
    }

    override fun loadMoreTest() {
        val beanArrayList = ArrayList<TestBean>()
        for (i in 0..9) {
            beanArrayList.add(TestBean("more: $i"))
        }
        mView.showTestList(beanArrayList, true)
    }

    override fun refresh() {
        val beanArrayList = ArrayList<TestBean>()
        for (i in 0..9) {
            beanArrayList.add(TestBean("refresh: $i"))
        }
        mView.showTestList(beanArrayList, false)
    }
}
