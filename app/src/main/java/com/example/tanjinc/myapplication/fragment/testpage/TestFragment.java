package com.example.tanjinc.myapplication.fragment.testpage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanjinc.myapplication.R;
import com.example.tanjinc.myapplication.bean.TestBean;

import java.util.List;

public class TestFragment extends Fragment implements TestContract.View {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TestAdapter mAdapter;
    private TestContract.Presenter mPresenter;


    public TestFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_list_layout, container, false);
        mAdapter = new TestAdapter();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //判断RecyclerView的状态 是空闲时，且是最后一个可见的item时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    mPresenter.loadMoreTest();
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                //The last visible item
                if (layoutManager != null) {
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                }
            }
        });
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });
        mPresenter.loadTest();
        return view;
    }

    @Override
    public void showTestList(List<TestBean> dataList, boolean isAppend) {
        if (isAppend) {
            mAdapter.addFootItems(dataList);
        } else {
            mAdapter.setData(dataList);
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void setPresenter(TestContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
