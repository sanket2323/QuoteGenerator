package com.example.quotegenerator

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.quotegenerator.ApiService.DataModel
import com.example.quotegenerator.ApiService.DataModelItem


@Composable
fun QuoteGeneratorApp(modifier: Modifier = Modifier) {

    val quotaViewModel: MainViewModel = viewModel()
    val viewState by quotaViewModel.state


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
    ) {


        when {
            viewState.isLoading -> {
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(
                    text = "Error occured while loading the data",
                    modifier = Modifier.padding(8.dp)
                )
            }

            else -> {
                QuotaList(list = viewState.list)
            }

        }

        Button(
            onClick = {
                quotaViewModel.fetchDate()
            },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Generate")
        }
    }
}


@Composable
fun QuotaList(list: ArrayList<DataModelItem>) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(0.dp, 50.dp)
    ) {
        items(list) {
            QuotaListItem(item = it)
        }
    }

}


@Composable
fun QuotaListItem(item: DataModelItem) {

    Card(
        modifier = Modifier
            .fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = item.q, fontFamily = FontFamily.Monospace, color = Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                text = ": ${item.a}",
                fontFamily = FontFamily.Cursive,
                textAlign = TextAlign.End,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }


}


