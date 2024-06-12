package com.example.quotegenerator.Api

import com.example.quotegenerator.quoteGeneratorViewModel
import retrofit2.http.GET

interface QuotaApi {
    @GET("api/random")
    suspend fun getQuote(): retrofit2.Response<QuotaModel>
}