package me.ashutoshkk.stocksapp.data.repository

import me.ashutoshkk.stocksapp.data.remote.AlphaVantageApiService
import me.ashutoshkk.stocksapp.data.remote.dto.TopGainersLosersDto
import me.ashutoshkk.stocksapp.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val apiService: AlphaVantageApiService): HomeRepository {

    override suspend fun getGainersLosers(): TopGainersLosersDto = apiService.getTopGainersLosers()

}