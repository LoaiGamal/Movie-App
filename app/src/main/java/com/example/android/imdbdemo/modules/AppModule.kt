package com.example.android.imdbdemo.modules

import com.example.android.imdbdemo.data.remote.TmdbEndPoints
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val appModules = module {
    // The Retrofit service using our custom HTTP client instance as a singleton
    single {
        createWebService<TmdbEndPoints>(
            okHttpClient = createHttpClient(),
            baseUrl = "https://api.themoviedb.org/3/"
        )
    }
}

inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}

/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .readTimeout(50, TimeUnit.SECONDS)
        .writeTimeout(50, TimeUnit.SECONDS)
        .connectTimeout(50, TimeUnit.SECONDS)
        .build()
}
//
//private val requestInterceptor: Interceptor = Interceptor { chain ->
//    val apiKey = "4kxJLzekA14QGA3XbQiiwVbYGvhW4q9q"
//    var request = chain.request()
//    val requestUrl = request.url
//
//    val url = requestUrl.newBuilder().addQueryParameter("api-key", apiKey).build()
//
//    request = request.newBuilder()
//        .url(url)
//        .build()
//
//    chain.proceed(request)
//}