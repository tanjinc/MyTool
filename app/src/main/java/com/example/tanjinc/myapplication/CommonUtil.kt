package com.example.tanjinc.myapplication

import android.os.Environment
import java.io.File

class CommonUtil private constructor() {
    var name:String ? = ""

    companion object {
        val instance: CommonUtil = CommonUtil()
    }

    public fun test() {
    }

    public fun getSDPath(): String {
        var sdDir: File? = null
        val sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory()//获取跟目录
        }
        return sdDir!!.toString()
    }
}