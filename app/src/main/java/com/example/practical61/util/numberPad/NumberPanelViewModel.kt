package com.example.practical61.util.numberPad

import androidx.lifecycle.ViewModel
import com.example.practical61.database.AppDatabase
import com.example.practical61.database.HistoryTable
import com.example.practical61.historyText
import com.example.practical61.outputText
import com.itis.libs.parserng.android.expressParser.MathExpression
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class NumberPanelViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {
    val openAlert = MutableStateFlow(false)
    private val operators = listOf("÷", "-", "+", "*", ".")

    fun onNumberPadClicked(data: String) {
        if (outputText == "0") {
            outputText = ""
        }
        if (data == "=" && outputText != "" &&
            !operators.contains(outputText) &&
            !operators.contains(outputText[outputText.lastIndex].toString())
        ) {
            if (outputText != historyText.substringAfterLast("=")) {
                performOperation()
            } else {
                outputText = "0"
            }
        } else if (data != "=") {
            outputText += data
        }
    }

    private fun performOperation() {
        historyText = if (historyText.lines().count() <= 3) {
            "$historyText\n$outputText"
        } else {
            outputText
        }
        outputText = MathExpression(outputText).solve().removeSuffix(".0")
        when {
            !outputText.contains("E") -> {
                if (outputText.contains(".") && outputText.substringAfter(".").length > 3) {
                    outputText =
                        outputText.substringBefore(".") + "." + outputText.substringAfter(".")
                            .substring(0, 4)
                }
            }
            outputText.contains("ERROR") -> {
                openAlert.value = true
                outputText = "0"
            }
        }
        historyText = "$historyText=$outputText"
        addHistoryToDatabase()
    }

    private fun addHistoryToDatabase() {
        appDatabase.historyDao()
            .insertHistory(HistoryTable(result = historyText.substringAfterLast("\n")))
    }

    fun updateAlertStatus() {
        openAlert.value = false
    }
}