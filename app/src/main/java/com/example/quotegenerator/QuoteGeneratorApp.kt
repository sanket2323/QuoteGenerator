package com.example.quotegenerator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotegenerator.ApiService.DataModel
import com.example.quotegenerator.ApiService.DataModelItem


@Composable
fun QuoteGeneratorApp(modifier: Modifier = Modifier){

    val quotaViewModel:MainViewModel = viewModel()
    val viewState by quotaViewModel.state

    val gradientColors = listOf(
        Color(0xFF000046),
        Color(0xFF1CB5E0)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(gradientColors))
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { quotaViewModel.fetchDate() }) {
            Text(text = "Generate")
        }

        when{
            viewState.isLoading -> {
                CircularProgressIndicator(modifier.align(Alignment.CenterHorizontally))
            }
            viewState.error != null ->  {
                Text(text = "Error occured while loading the data",
                        modifier = Modifier.padding(8.dp)
                    )
            }
            else ->{
                QuotaList(list = viewState.list)
            }

        }
    }
}


@Composable
fun QuotaList(list : ArrayList<DataModelItem>){

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        items(list){
            QuotaListItem(item = it)
        }
    }

}


@Composable
fun QuotaListItem(item: DataModelItem){

    Column {
        Text(text = item.h)
        Text(text = item.a)
    }

}


