package com.example.tanjinc.myapplication.fragment.apkpage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.tanjinc.myapplication.CommonUtil
import com.example.tanjinc.myapplication.DownloadUtil
import com.example.tanjinc.myapplication.R
import com.example.tanjinc.myapplication.fragment.BaseFragment
import com.example.tanjinc.myapplication.utils.AppUtils
import kotlinx.android.synthetic.main.fragment_app_layout.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.lang.Exception

data class AppBean(val name:String, val haveDownload:Boolean = false)

class AppFragment : BaseFragment(){
    private val TAG = "AppFragment"
    private val REQUEST_CODE_CONTACT = 101
    private val PROGRESS_BAR_MAX = 1000
    private val BASE_URL:String = "http://10.12.130.98:8000/"
    private var mFileName : String ?= null
    private lateinit var mServerIp : String
    private var mAdapter: MyAdapter ?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_app_layout, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar.max = PROGRESS_BAR_MAX
        ip.setText(BASE_URL)
        mServerIp = ip.text.toString()
        swipeRefreshLayout.setOnRefreshListener {
            fetchData()
            swipeRefreshLayout.isRefreshing = false
        }
        fetchData()

        mAdapter = MyAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = mAdapter
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
        return "APK服务器"
    }

    private fun fetchData() {
        GlobalScope.launch {
            var document:Document ?= null
            try {
                document = Jsoup.connect(mServerIp).get()
            } catch (e: Exception) {

            }
            if(document != null) {
                val aTags = document.getElementsByTag("li")
                var files = mutableListOf<AppBean>()
                for (item in aTags) {
                    var appBean = AppBean(item.text(), isExit(item.text()))
                    files.add(appBean)
                }

                launch(Dispatchers.Main) {
                    mAdapter?.setData(files)
                }
            }

        }
    }

    public fun downloadApp( fileName : String) {
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
        GlobalScope.launch {
            val targetFilePath = CommonUtil.instance.getSDPath()+"/"+fileName
            DownloadUtil.downloadFile(mServerIp, fileName, targetFilePath, object: DownloadUtil.DownloadCallback {
                override fun onFail(message: String?) {
                    launch (Dispatchers.Main){
                        toast("download onFail $message")
                    }
                }

                override fun onSuccess() {
                    launch (Dispatchers.Main){
                        toast("下载成功")
                    }
                }

                override fun progress(progress: Float) {
                    Log.d(TAG, "progress $progress" )
                    launch(Dispatchers.Main) {
                        progressBar.progress = (progress * PROGRESS_BAR_MAX).toInt()
                    }
                }

            })
        }
    }

    fun isExit(fileName: String):Boolean {
        var file = File(CommonUtil.instance.getSDPath()+"/"+fileName)
        return file.exists()
    }

    inner class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
        private var appArray = mutableListOf<AppBean>()

        public fun setData(list: MutableList<AppBean>){
            appArray = list
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyViewHolder {
            var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.app_item_layout, null)
            return MyViewHolder(view)
        }

        override fun getItemCount(): Int {
            return appArray.size
        }

        override fun onBindViewHolder(viewholder: MyViewHolder, position: Int) {
            var appBean = appArray[position]
            viewholder.apply {
                viewholder.appNameTv.text = appBean.name
                viewholder.appNameTv.setTextColor(if (appBean.haveDownload) Color.BLUE else Color.BLACK)
                viewholder.appNameTv.setOnClickListener {
                    if (!appBean.haveDownload) {
                        downloadApp(appBean.name)
                        viewholder.appNameTv.setTextColor(Color.BLUE)
                    } else {
                        toast("已经下载成功,文件管理器安装")
//                        AppUtils.installApp(CommonUtil.instance.getSDPath()+"/"+appBean.name)
                    }
                }
            }

        }

    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appNameTv : TextView = itemView.findViewById(R.id.appNameTv)
    }

    fun Context.Toast(msg:String) {
        android.widget.Toast.makeText(context, msg, android.widget.Toast.LENGTH_LONG).show()
    }
}