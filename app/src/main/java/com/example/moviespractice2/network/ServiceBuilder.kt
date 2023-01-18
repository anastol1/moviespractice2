package com.example.moviespractice2.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    var client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            var request = chain.request()
            val url = request.url
                .newBuilder()
                .addQueryParameter("api_key", "71ab1b19293efe581c569c1c79d0f004")
                .build()
            request = request
                .newBuilder()
                .url(url)
                .build()
            chain.proceed(request)
        }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())

    fun buildService(): API {
        return retrofit.build().create(API::class.java)
    }
}