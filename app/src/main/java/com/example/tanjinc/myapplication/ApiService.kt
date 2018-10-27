package com.example.tanjinc.myapplication

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


interface ApiService {

    @Streaming
    @GET
    fun downloadFileWithDynamicUrlAsync(@Url fileUrl: String): Call<ResponseBody>
}