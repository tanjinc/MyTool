package com.example.tanjinc.myapplication.bean

import com.example.tanjinc.myapplication.BaseConstant

class SkinBean(var name: String?) : BaseBean() {

    override fun getType(): Int {
        return BaseConstant.TYPE_VIDEO
    }
}
