package com.example.tanjinc.myapplication.fragment.gridpage

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.bean.BaseBean


class GridFragment : Fragment(), GridContract.View {

    private var mRecyclerView: RecyclerView? = null
    private var mSwipeRefreshLayout: SwipeRefreshLayout? = null
    private var mAdapter: VideoAdapter? = null
    private var mPresenter: GridContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.skin_list_layout, container, false)
        mAdapter = VideoAdapter()
        mRecyclerView = view.findViewById<View>(R.id.recycler_view) as RecyclerView
        val gridLayoutManager = GridLayoutManager(activity, 4)
        mRecyclerView!!.layoutManager = gridLayoutManager
        mRecyclerView!!.adapter = mAdapter
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            internal var lastVisibleItem: Int = 0

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                //判断RecyclerView的状态 是空闲时，且是最后一个可见的item时才加载
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter!!.itemCount) {
                    mPresenter!!.loadMoreData()
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
                //The last visible item
                if (layoutManager != null) {
                    lastVisibleItem = layoutManager.findLastVisibleItemPosition()
                }
            }
        })
        mSwipeRefreshLayout = view.findViewById<View>(R.id.refreshLayout) as SwipeRefreshLayout
        mSwipeRefreshLayout!!.setOnRefreshListener { mPresenter!!.refresh() }
        mPresenter!!.loadData()
        return view
    }

    override fun showDataList(dataList: List<BaseBean>, isAppend: Boolean) {
        if (isAppend) {
            mAdapter!!.addFootItems(dataList)
        } else {
            mAdapter!!.setData(dataList)
        }
        mSwipeRefreshLayout!!.isRefreshing = false
    }

    override fun setPresenter(presenter: GridContract.Presenter) {
        mPresenter = presenter
    }
}
