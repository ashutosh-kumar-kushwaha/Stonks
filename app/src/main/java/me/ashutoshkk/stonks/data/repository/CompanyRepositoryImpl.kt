package me.ashutoshkk.stonks.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.CompanyDto
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto
import me.ashutoshkk.stonks.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val apiService: AlphaVantageApiService) :
    CompanyRepository {

    override suspend fun getCompanyDetails(ticker: String): CompanyDto =
        withContext(Dispatchers.IO) {
            apiService.getCompanyDetails(ticker)
        }

    override suspend fun getIntraDayPrices(ticker: String): GraphDataDto =
        withContext(Dispatchers.IO) {
            apiService.getIntaDayPrices(ticker)
        }

    override suspend fun getDailyPrices(ticker: String): GraphDataDto =
        withContext(Dispatchers.IO) {
            apiService.getDailyPrices(ticker)
        }

    override suspend fun getMonthlyPrices(ticker: String): GraphDataDto =
        withContext(Dispatchers.IO) {
            apiService.getMonthlyPrices(ticker)
        }

}