package com.example.practical61.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryTable::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}