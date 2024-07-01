package me.ashutoshkk.stonks.data.remote

import me.ashutoshkk.stonks.common.Constants.API_KEY
import me.ashutoshkk.stonks.data.remote.dto.CompanyDto
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.data.remote.dto.SearchDto
import me.ashutoshkk.stonks.data.remote.dto.TopGainersLosersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AlphaVantageApiService {

    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getTopGainersLosers(
        @Query("apikey") apiKey: String = API_KEY,
    ): TopGainersLosersDto

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyDetails(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): CompanyDto

    @GET("query?function=TIME_SERIES_INTRADAY")
    suspend fun getIntaDayPrices(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("interval") interval: String = "60min"
    ): GraphDataDto

    @GET("query?function=TIME_SERIES_DAILY")
    suspend fun getDailyPrices(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): GraphDataDto

    @GET("query?function=TIME_SERIES_MONTHLY")
    suspend fun getMonthlyPrices(
        @Query("symbol") ticker: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): GraphDataDto

    @GET("query?function=SYMBOL_SEARCH")
    suspend fun search(
        @Query("keywords") query: String,
        @Query("apikey") apiKey: String = API_KEY,
    ): SearchDto

}