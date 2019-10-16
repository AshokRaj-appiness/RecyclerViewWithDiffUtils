package com.example.recyclerviewwithdiffutils.repository

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class Repository(val baseUrl:String, val isDebug:Boolean) {
  val retrofit:Retrofit
  init{
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if(isDebug)
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    else
      httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

    val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

    retrofit = Retrofit.Builder().baseUrl(baseUrl).client(client)
              .addConverterFactory(GsonConverterFactory.create())
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
  }
}

