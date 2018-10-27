package com.example.tanjinc.myapplication

import android.os.Environment
import android.util.Log
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.*
import android.os.Environment.getExternalStorageDirectory



public class DownloadUtil private constructor() {
    private final val TAG = "DownloadUtil"

    private val BASE_URL:String = "http://192.168.232.158:8000/"

    companion object {
        val instance: DownloadUtil = DownloadUtil()
    }

    fun download(fileName:String, path:String) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build()
        val apiService = retrofit.create(ApiService::class.java)
        val call = apiService.downloadFileWithDynamicUrlAsync(fileName)
        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d(TAG, t.message)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                writeResponseBodyToDisk(response.body()!!, getSDPath()+"/"+fileName)
            }
        })
    }
    private fun writeResponseBodyToDisk(body:ResponseBody, targetUrl: String) : Boolean{
        try {
            // todo change the file location/name according to your needs
            val futureStudioIconFile = File(targetUrl)

            var inputStream: InputStream ?= null
            var outputStream: OutputStream ?= null

            try {
                val fileReader = ByteArray(4096)
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
                }

                outputStream.flush()

                return true
            } catch (e : IOException) {
                Log.e(TAG, e.message);
                return false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            return false
        }
    }

    private fun getSDPath(): String {
        var sdDir: File? = null
        val sdCardExist = Environment.getExternalStorageState()
                .equals(android.os.Environment.MEDIA_MOUNTED)//判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory()//获取跟目录
        }
        return sdDir!!.toString()
    }
}