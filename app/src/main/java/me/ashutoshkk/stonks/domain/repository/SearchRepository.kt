package me.ashutoshkk.stonks.domain.repository

import me.ashutoshkk.stonks.data.remote.dto.SearchDto
import me.ashutoshkk.stonks.data.room.SearchHistory

interface SearchRepository {

    suspend fun search(query: String): SearchDto

    suspend fun getSearchHistory(): List<SearchHistory>

    suspend fun addToSearchHistory(history: SearchHistory)

}