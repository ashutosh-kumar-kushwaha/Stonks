package me.ashutoshkk.stonks.data.remote

import me.ashutoshkk.stonks.common.Constants.API_KEY
import me.ashutoshkk.stonks.data.remote.dto.TopGainersLosersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApiService {

    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getTopGainersLosers(
        @Query("apikey") apiKey: String = API_KEY,
    ): TopGainersLosersDto

}