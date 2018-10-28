package com.example.tanjinc.myapplication.fragment.apkpage

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tanjinc.myapplication.CommonUtil
import com.example.tanjinc.myapplication.DownloadUtil
import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_app_layout.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

class AppFragment : BaseFragment(){
    private val TAG = "AppFragment"
    private val REQUEST_CODE_CONTACT = 101
    private val PROGRESS_BAR_MAX = 1000
    private val BASE_URL:String = "http://192.168.232.158:8000/"
    private var mFileName : String ?= null
    private lateinit var mServerIp : String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_app_layout, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.max = PROGRESS_BAR_MAX
        mServerIp = ip.text.toString()
        swipeRefreshLayout.setOnRefreshListener {
            fileContainer.removeAllViews()
            fetchData()
            swipeRefreshLayout.isRefreshing = false
        }
        fetchData()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            REQUEST_CODE_CONTACT ->
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    downloadApp(mFileName!!)
                }
        }
    }

    override fun getName(): String {
        return "ServerFiles"
    }

    private fun fetchData() {
        launch {
            var document: Document = Jsoup.connect(mServerIp).get()
            val aTags = document.getElementsByTag("li")
            var files = mutableListOf<String>()
            for (item in aTags) {
                files.add(item.text())
            }

            launch(UI) {
                for (file in files) {
                    var textView = TextView(getContext())
                    textView.text = file
                    textView.setPadding(0, 10, 0, 10)
                    textView.setOnClickListener {
                        textView.setTextColor(Color.BLUE)
                        downloadApp(file)
                    }
                    fileContainer.addView(textView)
                }
            }
        }
    }

    private fun downloadApp( fileName : String) {
        if (Build.VERSION.SDK_INT >= 23) {
            val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            //验证是否许可权限
            if (activity?.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                mFileName = fileName
                this.requestPermissions(permissions, REQUEST_CODE_CONTACT)
                return
            }
        }
        //launch()表示创建和启动一个Coroutine
        progressBar.progress = 0
        launch (CommonPool) {
            val targetFilePath = CommonUtil.instance.getSDPath()+"/"+fileName
            DownloadUtil.downloadFile(mServerIp, fileName, targetFilePath, object: DownloadUtil.DownloadCallback {
                override fun onFail(message: String?) {
                    launch (UI){
                        toast("download onFail $message")
                    }
                }

                override fun onSuccess() {
                    launch (UI){
                        toast("download onSuccess")
                    }
                }

                override fun progress(progress: Float) {
                    Log.d(TAG, "progress $progress" )
                    launch(UI) {
                        progressBar.progress = (progress * PROGRESS_BAR_MAX).toInt()
                    }
                }

            })
        }
    }
}