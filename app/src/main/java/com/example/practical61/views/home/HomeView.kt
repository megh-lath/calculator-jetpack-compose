package com.example.practical61.views.home

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.practical61.R
import com.example.practical61.historyText
import com.example.practical61.outputText
import com.example.practical61.ui.theme.Teal200
import com.example.practical61.util.DividedLineView
import com.example.practical61.util.numberPad.NumberPanel
import com.example.practical61.views.history.HistoryView

@Composable
fun HomeView() {
    val viewModel = hiltViewModel<HomeViewModel>()
    val viewHistory = viewModel.openHistoryView.collectAsState()
    BackHandler { viewModel.openHistoryView.value = false }
    val scrollState = rememberScrollState()
    Box(contentAlignment = Alignment.Center) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.top_app_bar_title),
                        fontSize = 20.sp,
                        color = Color.White,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Start,
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.ExitToApp,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Teal200,
                contentColor = Color.White,
                elevation = 4.dp
            )
            Row(
                modifier = Modifier
                    .weight(0.4f)
                    .padding(top = 16.dp)
                    .padding(4.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.End,
                ) {
                    Text(
                        text = historyText,
                        fontSize = 20.sp,
                        maxLines = 4,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(24.dp)
                    )
                    Text(
                        text = outputText,
                        fontSize = 28.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
            DividedLineView()
            Column(
                modifier = Modifier
                    .weight(0.6f)
                    .fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .weight(0.1f)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    if (!viewHistory.value) {
                        Row {
                            IconButton(
                                onClick = {
                                    viewModel.openHistoryView.value = true
                                },
                                modifier = Modifier.padding(start = 24.dp, top = 20.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_baseline_history_24),
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                            IconButton(
                                onClick = {
                                    viewModel.clearDisplay()
                                },
                                modifier = Modifier.padding(start = 24.dp, top = 20.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        }
                        IconButton(
                            onClick = {
                                viewModel.removeLastCharacter()
                            },
                            modifier = Modifier.padding(end = 24.dp, top = 20.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    } else {
                        IconButton(
                            onClick = {
                                viewModel.openHistoryView.value = false
                            },
                            modifier = Modifier.padding(start = 24.dp, top = 20.dp)
                        ) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.weight(0.9f),
                    verticalAlignment = Alignment.Top
                ) {
                    if (!viewHistory.value) {
                        NumberPanel()
                    } else {
                        HistoryView()
                    }
                }
            }
        }
    }
}