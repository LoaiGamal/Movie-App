package com.example.android.imdbdemo.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()
    private var service: TmdbEndPoints? = null
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

//    fun <T> buildService(service: Class<T>): T {
//        return retrofit.create(service)
//    }

    fun getService(): TmdbEndPoints {
        return if (null == service) {
            service = retrofit.create(TmdbEndPoints::class.java)
            service!!
        } else
            service!!
    }
}