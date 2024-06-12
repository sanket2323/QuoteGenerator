package com.example.quotegenerator.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroFitInstance {

    private val baseUrl = "https://zenquotes.io/"

    private fun getInstance():Retrofit{
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val quotaApi:QuotaApi = getInstance().create(QuotaApi::class.java)
}