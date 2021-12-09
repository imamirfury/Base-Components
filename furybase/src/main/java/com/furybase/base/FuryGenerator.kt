package com.furybase.base

import androidx.viewbinding.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/***
 * Created By Amir Fury on December 9 2021
 *
 * Email: Fury.amir93@gmail.com
 * */


object ServiceGenerator {

    inline operator fun <reified T> invoke(
        baseUrl: String,
        connectionTimeOut: Long,
        readTimeOut: Long,
        interceptor: Interceptor?
    ): T {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }

        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(connectionTimeOut, TimeUnit.SECONDS)
            readTimeout(readTimeOut, TimeUnit.SECONDS)
            addInterceptor(loggingInterceptor)
            if (interceptor != null) {
                addInterceptor(interceptor)
            }
        }.build()

        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create()).build().create(T::class.java)
    }
}