package com.example.quotegenerator.ApiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object retrofitInstance {
    private val baseUrl = "https://zenquotes.io"

    private val retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val quotaService = retrofit.create(ApiService::class.java)
}