package me.ashutoshkk.stonks.domain.repository

import me.ashutoshkk.stonks.data.remote.dto.CompanyDto
import me.ashutoshkk.stonks.data.remote.dto.GraphDataDto

interface CompanyRepository {

    suspend fun getCompanyDetails(ticker: String): CompanyDto

    suspend fun getDailyPrices(ticker: String): GraphDataDto

}