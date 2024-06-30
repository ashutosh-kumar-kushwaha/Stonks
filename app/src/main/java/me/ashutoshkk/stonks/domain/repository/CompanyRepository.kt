package me.ashutoshkk.stonks.domain.repository

import me.ashutoshkk.stonks.data.remote.dto.CompanyDto

interface CompanyRepository {

    suspend fun getCompanyDetails(ticker: String): CompanyDto

}