package com.example.tanjinc.myapplication.fragment.gridpage

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList

import com.example.tanjinc.myapplication.BaseConstant
import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.bean.ABean
import com.example.tanjinc.myapplication.bean.BBean
import com.example.tanjinc.myapplication.bean.BaseBean
import com.example.tanjinc.myapplication.bean.SkinBean

class VideoAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val mDataArray = ArrayList<BaseBean>()

    fun setData(items: List<BaseBean>) {
        mDataArray.clear()
        mDataArray.addAll(items)
        notifyDataSetChanged()
    }

    fun addFootItems(items: List<BaseBean>) {
        mDataArray.addAll(items)
        notifyDataSetChanged()
    }

    fun addHeaderItems(items: List<BaseBean>) {
        mDataArray.addAll(0, items)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            BaseConstant.TYPE_A -> {
                val viewA = layoutInflater.inflate(R.layout.list_item_a, parent, false)
                return AViewHolder(viewA)
            }
            BaseConstant.TYPE_B -> {
                val viewB = layoutInflater.inflate(R.layout.list_item_b, parent, false)
                return BViewHolder(viewB)
            }
            BaseConstant.TYPE_VIDEO -> {
                val view = layoutInflater.inflate(R.layout.list_item_video, parent, false)
                return VideoViewHolder(view)
            }
            else -> {
                val view = layoutInflater.inflate(R.layout.list_item_video, parent, false)
                return VideoViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder == null) {
            return
        }
        when (getItemViewType(position)) {
            BaseConstant.TYPE_A -> {
                bindTypeAViewHolder(viewHolder, position)
                return
            }
            BaseConstant.TYPE_B -> bindTypeBViewHolder(viewHolder, position)
            BaseConstant.TYPE_VIDEO -> bindTypeVideoViewHolder(viewHolder, position)
        }
    }

    private fun bindTypeAViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder !is AViewHolder) {
            return
        }
        val (name) = mDataArray[position] as ABean
        viewHolder.mItemName.text = name
    }

    private fun bindTypeBViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder !is BViewHolder) {
            return
        }
        val bBean = mDataArray[position] as BBean
        viewHolder.itemNameTv.text = bBean.name
    }

    private fun bindTypeVideoViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder !is VideoViewHolder) {
            return
        }
        val vBean = mDataArray[position] as SkinBean
        viewHolder.itemNameTv.text = vBean.name
    }

    override fun getItemCount(): Int {
        return mDataArray.size
    }


    override fun getItemViewType(position: Int): Int {
        return mDataArray[position].getType()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val gridLayoutManager = layoutManager as GridLayoutManager?
            gridLayoutManager!!.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val type = getItemViewType(position)
                    when (type) {
                        BaseConstant.TYPE_A -> return 1
                        BaseConstant.TYPE_B -> return 1
                        BaseConstant.TYPE_VIDEO -> return 2
                    }
                    return 3
                }
            }
        }
        recyclerView.addItemDecoration(GridItemDecoration())
    }

    private inner class GridItemDecoration : RecyclerView.ItemDecoration() {

        private val mDivider: Drawable? = null

        override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val layoutManager = parent.layoutManager as GridLayoutManager?
            val lookup = layoutManager!!.spanSizeLookup


            if (mDivider == null || layoutManager.childCount == 0) {
                return
            }

            val spanCount = layoutManager.spanCount
            if (layoutManager.orientation == GridLayoutManager.VERTICAL) {

            }
            super.onDraw(c, parent, state)
        }

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.bottom = 8
            outRect.left = 10
            outRect.right = 10
        }
    }
}
