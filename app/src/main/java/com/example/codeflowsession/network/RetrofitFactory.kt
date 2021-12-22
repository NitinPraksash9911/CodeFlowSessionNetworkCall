package com.example.codeflowsession.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {

    private val retrofit: Retrofit by lazy {

        Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    val userApiService: UserApiService = retrofit.create(UserApiService::class.java)

}