package com.example.tanjinc.myapplication.fragment.testpage

import com.example.tanjinc.myapplication.bean.TestBean

class TestContract {

    interface View {
        fun showTestList(TestList: List<TestBean>, isAppend: Boolean)

        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun loadTest()        //加载数据

        fun loadMoreTest()    //加载更多

        fun refresh()          //刷新
    }
}
