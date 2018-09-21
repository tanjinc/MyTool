package com.example.tanjinc.myapplication.fragment.gridpage;

import com.example.tanjinc.myapplication.bean.BaseBean;

import java.util.List;

public class GridContract {

    public interface View {
        void showDataList(List<? extends BaseBean> dataList, boolean isAppend);

        void setPresenter(Presenter presenter);
    }

    public interface Presenter {
        void loadData();       //加载数据

        void loadMoreData();   //加载更多

        void refresh();         //刷新
    }
}
