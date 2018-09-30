package com.example.tanjinc.myapplication.bean

import com.example.tanjinc.myapplication.BaseConstant
import com.google.gson.annotations.SerializedName

data class ABean(var name: String) : BaseBean() {
    /**
     * code : 1
     * info : success
     * results : {"id":"1","name":"hehe"}
     */

    var code: Int = 0
    var info: String? = null
    var results: ResultsBean? = null

    override fun getType(): Int {
        return BaseConstant.TYPE_A
    }

    class ResultsBean {
        /**
         * id : 1
         * name : hehe
         */

        var id: String? = null
        @SerializedName("name")
        var nameX: String? = null
    }
}
