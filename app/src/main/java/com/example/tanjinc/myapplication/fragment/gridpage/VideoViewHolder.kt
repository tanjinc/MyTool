package com.example.tanjinc.myapplication.fragment.gridpage

import android.view.View
import android.widget.TextView

import com.example.tanjinc.myapplication.BaseConstant
import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.view.BaseViewHolder
import kotlinx.android.synthetic.main.list_item_a.view.*


class VideoViewHolder(itemView: View) : BaseViewHolder(itemView) {
    var itemNameTv: TextView

    init {
//        itemNameTv = itemView.findViewById<View>(R.id.item_name) as TextView
        itemNameTv = itemView.item_name
    }

    fun getType(): Int {
        return BaseConstant.TYPE_VIDEO
    }
}
