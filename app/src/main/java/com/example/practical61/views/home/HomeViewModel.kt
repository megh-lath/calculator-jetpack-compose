package com.example.practical61.views.home

import androidx.lifecycle.ViewModel
import com.example.practical61.historyText
import com.example.practical61.outputText
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {
    val openHistoryView = MutableStateFlow(false)

    fun removeLastCharacter() {
        if (outputText != "") {
            outputText = outputText.substring(0, (outputText.length - 1))
        }
    }

    fun clearDisplay() {
        outputText = "0"
        historyText = ""
    }
}