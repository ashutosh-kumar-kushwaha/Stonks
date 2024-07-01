package me.ashutoshkk.stonks.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SearchHistory::class],
    version = 1
)
abstract class StonksDatabase : RoomDatabase() {
    abstract val searchHistoryDao: SearchHistoryDao
}