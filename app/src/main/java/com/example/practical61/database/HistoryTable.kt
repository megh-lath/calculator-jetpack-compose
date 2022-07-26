package com.example.practical61.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryTable(@field:ColumnInfo(name = "result") val result: String) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}