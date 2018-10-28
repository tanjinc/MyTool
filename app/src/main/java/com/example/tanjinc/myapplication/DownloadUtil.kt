package com.example.tanjinc.myapplication

import android.os.AsyncTask
import android.os.Environment
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import android.os.Environment.getExternalStorageDirectory
import kotlinx.coroutines.experimental.async


public object DownloadUtil {
    private final val TAG = "DownloadUtil"

    private val BASE_URL:String = "http://192.168.232.158:8000/"

    interface DownloadCallback {
        fun onFail(message : String?)
        fun onSuccess()
        fun progress(progress:Float)
    }

    /**
     * 同步操作
     */
    fun downloadFile(serverIp:String, fileName:String, target:String, callback: DownloadCallback? = null) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(serverIp)
                .build()
        val apiService = retrofit.create(ApiService::class.java)
        var response = apiService.downloadFile(fileName).execute()//同步
        if (response.body() != null){
            val ret = writeResponseBodyToDisk(response.body(), target, callback)
            if (ret) {
                callback?.onSuccess()
            } else {
                callback?.onFail(response.message())
            }
        } else {
            Log.e(TAG, response.message())
            callback?.onFail(response.message())
        }
    }
    private fun writeResponseBodyToDisk(body:ResponseBody ?, targetUrl: String, callback: DownloadCallback?) : Boolean{
        if (body == null) {
            return false
        }

        var inputStream: InputStream ?= null
        var outputStream: OutputStream ?= null

        try {
            val futureStudioIconFile = File(targetUrl)
            val fileReader = ByteArray(8 * 1024 * 1024)
            val fileSize: Long = body.contentLength()
            var fileSizeDownloaded = 0

            inputStream = body.byteStream()
            outputStream = FileOutputStream(futureStudioIconFile)

            while (true) {
                var read = inputStream.read(fileReader)
                if (read == -1) {
                    break
                }
                outputStream.write(fileReader, 0, read)
                fileSizeDownloaded += read
                Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                callback?.progress(fileSizeDownloaded * 1.0f /fileSize)
            }
            outputStream.flush()
            return true
        } catch (e : IOException) {
            Log.e(TAG, e.message)
            return false
        } finally {
            inputStream?.close()
            outputStream?.close()
        }
    }
}