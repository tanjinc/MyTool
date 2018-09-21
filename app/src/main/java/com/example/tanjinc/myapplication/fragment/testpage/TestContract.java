package com.example.tanjinc.myapplication.fragment.testpage;

import com.example.tanjinc.myapplication.bean.TestBean;

import java.util.List;

public class TestContract {

    public interface View {
        void showTestList(List<TestBean> TestList, boolean isAppend);

        void setPresenter(Presenter presenter);
    }

    public interface Presenter {
        void loadTest();       //加载数据

        void loadMoreTest();   //加载更多

        void refresh();         //刷新
    }
}
