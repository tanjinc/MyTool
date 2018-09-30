package com.example.tanjinc.myapplication.fragment.gridpage

import com.example.tanjinc.myapplication.bean.BaseBean

class GridContract {

    interface View {
        fun showDataList(dataList: List<BaseBean>, isAppend: Boolean)

        fun setPresenter(presenter: Presenter)
    }

    interface Presenter {
        fun loadData()        //加载数据

        fun loadMoreData()    //加载更多

        fun refresh()          //刷新
    }
}
