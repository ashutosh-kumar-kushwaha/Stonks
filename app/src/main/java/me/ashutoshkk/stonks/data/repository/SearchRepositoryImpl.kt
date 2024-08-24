package me.ashutoshkk.stonks.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.SearchDto
import me.ashutoshkk.stonks.data.room.SearchHistory
import me.ashutoshkk.stonks.data.room.SearchHistoryDao
import me.ashutoshkk.stonks.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: AlphaVantageApiService,
    private val searchHistoryDao: SearchHistoryDao
) : SearchRepository {

    override suspend fun search(query: String): SearchDto = withContext(Dispatchers.IO) {
        apiService.search(query)
    }

    override fun getSearchHistory(): Flow<List<SearchHistory>> = searchHistoryDao.getAllSearches()

    
    override suspend fun addToSearchHistory(history: SearchHistory) = withContext(Dispatchers.IO) {
        searchHistoryDao.insertWithLimit(history)
    }

}