package me.ashutoshkk.stonks.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.TopGainersLosersDto
import me.ashutoshkk.stonks.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: AlphaVantageApiService) :
    HomeRepository {

    override suspend fun getGainersLosers(): TopGainersLosersDto = withContext(Dispatchers.IO) {
        apiService.getTopGainersLosers()
    }

}