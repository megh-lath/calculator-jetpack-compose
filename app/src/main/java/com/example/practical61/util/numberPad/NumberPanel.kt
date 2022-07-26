package com.example.practical61.util.numberPad

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.practical61.R
import com.example.practical61.ui.theme.Teal700
import com.example.practical61.util.showBanner


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NumberPanel() {
    val viewModel = hiltViewModel<NumberPanelViewModel>()
    val context = LocalContext.current
    val openAlert =
        viewModel.openAlert.collectAsState()
    if (openAlert.value) {
        showBanner(context, stringResource(R.string.input_error_message))
        viewModel.updateAlertStatus()
    }

    val data =
        listOf("7", "8", "9", "÷", "4", "5", "6", "-", "1", "2", "3", "+", ".", "0", "*", "=")
    val operators = listOf("÷", "-", "+", "*", ".", "=")
    LazyVerticalGrid(
        cells = GridCells.Fixed(4),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(data.count()) { index ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .clickable(
                        onClick = {
                            viewModel.onNumberPadClicked(data[index])
                        }
                    )
            ) {
                Text(
                    text = data[index],
                    fontSize = 24.sp,
                    color = if (operators.contains(data[index])) Teal700
                    else Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
            }
        }
    }
}