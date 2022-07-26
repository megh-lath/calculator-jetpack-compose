package com.example.practical61.util

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun DividedLineView() {
    Divider(
        Modifier
            .fillMaxWidth(),
        thickness = 1.dp,
        color = Color.Gray
    )
}