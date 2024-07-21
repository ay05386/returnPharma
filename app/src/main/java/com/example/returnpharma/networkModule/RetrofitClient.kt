package com.example.returnpharma.networkModule

import android.util.Log
import com.example.returnpharma.remote.RxMaxApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://portal-test.rxmaxreturns.com/rxmax/"


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .addInterceptor { chain ->
            val original = chain.request()
            val token = SessionManager.getToken()

            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "*/*")

            if (token != null) {
                requestBuilder.header("Authorization", "Bearer $token")
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    val instance: RxMaxApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(RxMaxApi::class.java)
    }
}