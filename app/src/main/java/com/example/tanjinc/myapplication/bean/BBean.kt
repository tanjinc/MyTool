package com.example.tanjinc.myapplication.bean

import com.example.tanjinc.myapplication.BaseConstant

class BBean(name: String) : BaseBean() {
    var name: String
        internal set

    init {
        this.name = name
    }

    override fun getType(): Int {
        return BaseConstant.TYPE_B
    }
}
