package com.example.practical61.views.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.practical61.database.HistoryTable
import com.example.practical61.ui.theme.Teal500
import com.example.practical61.util.showBanner

@Composable
fun HistoryView() {
    val viewModel = hiltViewModel<HistoryViewModel>()
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit, block = {
        viewModel.onStart()
    })
    Box(contentAlignment = Alignment.Center) {
        Column {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(4.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    val state by viewModel.state.collectAsState()
                    state.let {
                        when (it) {
                            HistoryState.LOADING -> {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator(color = Teal500)
                                }
                            }
                            is HistoryState.SUCCESS -> {
                                val historyList = it.historyList
                                HistoryCardView(
                                    histories = historyList
                                )
                            }
                            is HistoryState.FAILURE -> {
                                val message = it.failureMessage
                                LaunchedEffect(key1 = message) {
                                    showBanner(context, message)
                                    viewModel.resetState()
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun HistoryCardView(histories: List<HistoryTable>) {
    LazyColumn(contentPadding = PaddingValues(24.dp)) {
        itemsIndexed(histories) { _, item ->
            Text(
                text = item.result,
                fontSize = 16.sp,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Divider(
                Modifier
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}
