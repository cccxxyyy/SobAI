package com.software.sobai.data.remote

import com.software.sobai.utils.SUNNY_BEACH_API_BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Author: xiaolianyi
 * @Date:  2025-10-14
 * @Description: 实际链接创造者

 */
class ServiceCreator {

    val retrofit: Retrofit by lazy { createRetrofit { baseUrl(SUNNY_BEACH_API_BASE_URL) } }

    private val okHttpClient by lazy { OkHttpClient.Builder().build() }

    private fun createRetrofit(block: Retrofit.Builder.() -> Retrofit.Builder): Retrofit {
        // 整个函数体变成一个流畅的链式表达式
        return Retrofit.Builder()
            .client(okHttpClient)
            // 使用 create() 而非 create(GsonFactory.getSingletonGson()) 来模拟第一个片段的简单配置
            .addConverterFactory(GsonConverterFactory.create())
            .run(block)
            // 最后调用 build()
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)


    //inline fun <reified T> create(): T = retrofit.create(T::class.java)
}