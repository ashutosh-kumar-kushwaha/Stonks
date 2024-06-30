package me.ashutoshkk.stonks.domain.repository

import me.ashutoshkk.stonks.data.remote.dto.TopGainersLosersDto

interface HomeRepository {

    suspend fun getGainersLosers(): TopGainersLosersDto

}