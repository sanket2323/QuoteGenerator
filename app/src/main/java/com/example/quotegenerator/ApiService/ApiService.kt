package com.example.quotegenerator.ApiService

import retrofit2.http.GET

interface ApiService {

    @GET("/api/quotes")
    suspend fun getData():DataModel
}