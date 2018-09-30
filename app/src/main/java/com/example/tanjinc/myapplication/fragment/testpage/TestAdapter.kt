package com.example.tanjinc.myapplication.fragment.testpage

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import android.widget.TextView

import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.bean.TestBean

class TestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mDataArray: MutableList<TestBean> = ArrayList()

    fun setData(items: MutableList<TestBean>) {
        mDataArray = items
        notifyDataSetChanged()
    }

    fun addFootItems(items: List<TestBean>) {
        mDataArray.addAll(items)
        notifyDataSetChanged()
    }

    fun addHeaderItems(items: List<TestBean>) {
        mDataArray.addAll(0, items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == ITEM_TYPE_LOADING) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.test_list_item_loading, parent, false)
            return LoadMoreViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.test_list_item, parent, false)
            return ViewHolder(view)
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ITEM_TYPE_LOADING) {
            val loadMoreViewHolder = viewHolder as LoadMoreViewHolder
            loadMoreViewHolder.loadMoreView.visibility = View.VISIBLE
        } else {
            val vh = viewHolder as ViewHolder
            vh.mItemName.text = mDataArray[position].title
        }
    }

    override fun getItemCount(): Int {
        return mDataArray.size + 1 //add Loading View
    }


    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1) {
            ITEM_TYPE_LOADING
        } else super.getItemViewType(position)
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        internal var mItemName: TextView

        init {
            mItemName = mView.findViewById<View>(R.id.item_number) as TextView
        }
    }

    inner class LoadMoreViewHolder(internal var loadMoreView: View) : RecyclerView.ViewHolder(loadMoreView)

    companion object {

        private val ITEM_TYPE_LOADING = 1000
    }
}
