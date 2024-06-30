package me.ashutoshkk.stonks.data.repository

import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.TopGainersLosersDto
import me.ashutoshkk.stonks.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: AlphaVantageApiService): HomeRepository {

    override suspend fun getGainersLosers(): TopGainersLosersDto = apiService.getTopGainersLosers()

}