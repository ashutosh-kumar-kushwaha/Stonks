package me.ashutoshkk.stonks.data.room

import androidx.room.*

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM search_history ORDER BY timestamp ASC")
    suspend fun getAllSearches(): List<SearchHistory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(search: SearchHistory)

    @Query("DELETE FROM search_history WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Transaction
    suspend fun insertWithLimit(search: SearchHistory) {
        val searches = getAllSearches()
        if (searches.size >= 5) {
            deleteById(searches[0].id)
        }
        insert(search)
    }
}