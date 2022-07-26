package com.example.practical61.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    fun insertHistory(history: HistoryTable)

    @Query("select * from history_table order by id desc")
    fun getHistory(): List<HistoryTable>
}