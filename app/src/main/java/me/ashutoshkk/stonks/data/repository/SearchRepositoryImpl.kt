package me.ashutoshkk.stonks.data.repository

import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.SearchDto
import me.ashutoshkk.stonks.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: AlphaVantageApiService
): SearchRepository {

    override suspend fun search(query: String): SearchDto = apiService.search(query)

}