package com.example.tanjinc.myapplication.fragment.gridpage

import android.view.View
import android.widget.TextView

import com.example.tanjinc.myapplication.BaseConstant
import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.view.BaseViewHolder

class BViewHolder(itemView: View) : BaseViewHolder(itemView) {

    var itemNameTv: TextView = itemView.findViewById(R.id.item_name)

    override fun getType(): Int {
        return BaseConstant.TYPE_B
    }
}
