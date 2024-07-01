package me.ashutoshkk.stonks.data.repository

import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.CompanyDto
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val apiService: AlphaVantageApiService) :
    CompanyRepository {

    override suspend fun getCompanyDetails(ticker: String): CompanyDto = apiService.getCompanyDetails(ticker)

    override suspend fun getIntraDayPrices(ticker: String): GraphDataDto = apiService.getIntaDayPrices(ticker)

    override suspend fun getDailyPrices(ticker: String): GraphDataDto = apiService.getDailyPrices(ticker)

    override suspend fun getMonthlyPrices(ticker: String): GraphDataDto = apiService.getMonthlyPrices(ticker)

}