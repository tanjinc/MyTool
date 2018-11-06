package com.example.tanjinc.myapplication

import android.content.Context
import android.content.Intent
import android.net.Uri
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

    public fun isFileExit(fileName:String):Boolean {
        return File(fileName).exists()
    }

    public fun installApp(context: Context, apkPath:String) {

        //apk文件的本地路径
        var apkFile = File(apkPath)
        //会根据用户的数据类型打开android系统相应的Activity。
        var intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse("file://" + apkFile.toString()), "application/vnd.android.package-archive")
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
        //开始安装
    }
}