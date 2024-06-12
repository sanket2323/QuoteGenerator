package com.example.quotegenerator

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotegenerator.Api.QuotaApi
import com.example.quotegenerator.Api.RetroFitInstance
import kotlinx.coroutines.launch
import kotlin.math.log

class quoteGeneratorViewModel:ViewModel() {

    private val quotaApi = RetroFitInstance.quotaApi

    fun getData(){
        viewModelScope.launch {


                val response = quotaApi.getQuote()
                if (response.isSuccessful){
                    println(response.body().toString())
//                    Log.i("Api:",)
                }
                else{
                    println("Error:"+ response.message())
//                    Log.i("Api",)
                }
        }
    }

}