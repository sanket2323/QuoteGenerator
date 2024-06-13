package com.example.quotegenerator

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotegenerator.ApiService.DataModel

import com.example.quotegenerator.ApiService.DataModelItem
import com.example.quotegenerator.ApiService.retrofitInstance.quotaService
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {

    data  class quotaState(
        val isLoading:Boolean = false,
        val error: String ? = null,
        val list: ArrayList<DataModelItem> = arrayListOf()
    )

    private val _state = mutableStateOf(quotaState())
    val state: MutableState<quotaState> = _state


     fun fetchDate(){
        viewModelScope.launch {
            _state.value = _state.value.copy(
                isLoading = true
            )
            try {
                val response =  quotaService.getData()
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = null,
                    list = response
                )
                println("Successful excucted")
            }
            catch (e:Exception){
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = "Error occurred will fetching data ${e.message}"
                )
                println("Error occurred")
            }
        }
    }

}