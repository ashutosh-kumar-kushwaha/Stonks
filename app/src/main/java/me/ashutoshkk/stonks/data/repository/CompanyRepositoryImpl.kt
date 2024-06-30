package me.ashutoshkk.stonks.data.repository

import me.ashutoshkk.stonks.data.remote.AlphaVantageApiService
import me.ashutoshkk.stonks.data.remote.dto.CompanyDto
import me.ashutoshkk.stonks.domain.repository.CompanyRepository
import javax.inject.Inject

class CompanyRepositoryImpl @Inject constructor(private val apiService: AlphaVantageApiService) :
    CompanyRepository {

    override suspend fun getCompanyDetails(ticker: String): CompanyDto = apiService.getCompanyDetails(ticker)

}