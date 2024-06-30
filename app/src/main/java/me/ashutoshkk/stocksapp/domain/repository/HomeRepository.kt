package me.ashutoshkk.stocksapp.domain.repository

import me.ashutoshkk.stocksapp.data.remote.dto.TopGainersLosersDto

interface HomeRepository {

    suspend fun getGainersLosers(): TopGainersLosersDto

}