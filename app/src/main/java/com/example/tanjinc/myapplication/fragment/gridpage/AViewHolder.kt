package com.example.tanjinc.myapplication.fragment.gridpage

import android.view.View
import android.widget.TextView

import com.example.tanjinc.myapplication.BaseConstant
import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.view.BaseViewHolder

class AViewHolder(var mView: View) : BaseViewHolder(mView) {
    var mItemName: TextView

    init {
        mItemName = mView.findViewById<View>(R.id.item_name) as TextView
    }

    override fun getType(): Int {
        return BaseConstant.TYPE_A
    }
}
