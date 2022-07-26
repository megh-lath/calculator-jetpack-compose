package com.example.practical61.views.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practical61.database.AppDatabase
import com.example.practical61.database.HistoryTable
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val appDatabase: AppDatabase
) : ViewModel() {
    val state = MutableStateFlow<HistoryState>(HistoryState.START)
    private fun loadHistory() = viewModelScope.launch {
        state.value = HistoryState.LOADING
        delay(1000)
        withContext(Dispatchers.IO) {
            state.value = HistoryState.SUCCESS(
                historyList = appDatabase.historyDao().getHistory()
            )
        }
    }

    fun onStart() {
        loadHistory()
    }

    fun resetState() {
        state.value = HistoryState.START
    }
}

sealed class HistoryState {
    object START : HistoryState()
    object LOADING : HistoryState()
    data class SUCCESS(
        val historyList: List<HistoryTable>
    ) : HistoryState()

    data class FAILURE(val failureMessage: String) : HistoryState()
}