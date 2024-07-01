package me.ashutoshkk.stonks.domain.repository

import kotlinx.coroutines.flow.Flow
import me.ashutoshkk.stonks.data.remote.dto.SearchDto
import me.ashutoshkk.stonks.data.room.SearchHistory

interface SearchRepository {

    suspend fun search(query: String): SearchDto

    fun getSearchHistory(): Flow<List<SearchHistory>>

    suspend fun addToSearchHistory(history: SearchHistory)

}