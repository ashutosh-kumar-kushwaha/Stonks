package me.ashutoshkk.stonks.domain.useCase

import com.patrykandpatrick.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatrick.vico.core.entry.FloatEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.stonks.common.Resource
import me.ashutoshkk.stonks.data.remote.dto.toCompany
import me.ashutoshkk.stonks.data.remote.dto.toDaily
import me.ashutoshkk.stonks.data.remote.dto.toIntraDay
import me.ashutoshkk.stonks.data.remote.dto.toMonthly
import me.ashutoshkk.stonks.domain.model.Company
import me.ashutoshkk.stonks.domain.model.GraphPoints
import me.ashutoshkk.stonks.domain.repository.CompanyRepository
import me.ashutoshkk.stonks.presentation.ui.company.GraphData
import retrofit2.HttpException
import javax.inject.Inject

class CompanyUseCase @Inject constructor(private val repository: CompanyRepository) {

    fun getCompanyDetails(ticker: String): Flow<Resource<Company>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getCompanyDetails(ticker).toCompany()))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred\nResponse code: ${e.code()}"
                )
            )
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server\nCheck your internet connection"))
            e.printStackTrace()
        }
    }

    fun getIntraDayPrices(ticker: String): Flow<Resource<GraphData>> = flow {
        emit(Resource.Loading())
        try {
            val data = repository.getIntraDayPrices(ticker).toIntraDay()
            val lineEntryModelData = mutableListOf<FloatEntry>()
            data.values.take(24).forEachIndexed { index, value ->
                lineEntryModelData.add(FloatEntry(index.toFloat(), value))
            }
            val chartEntryModelProducer = ChartEntryModelProducer(lineEntryModelData)
            emit(Resource.Success(GraphData(data.labels, chartEntryModelProducer)))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred\nResponse code: ${e.code()}"
                )
            )
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server\nCheck your internet connection"))
            e.printStackTrace()
        }
    }

    fun getDailyPrices(ticker: String): Flow<Resource<GraphPoints>> = flow {
        emit(Resource.Loading())
        try {
            val data = repository.getDailyPrices(ticker).toDaily()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred\nResponse code: ${e.code()}"
                )
            )
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server\nCheck your internet connection"))
            e.printStackTrace()
        }
    }

    fun getMonthlyPrices(ticker: String): Flow<Resource<GraphPoints>> = flow {
        emit(Resource.Loading())
        try {
            val data = repository.getMonthlyPrices(ticker).toMonthly()
            emit(Resource.Success(data))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: "An unexpected error occurred\nResponse code: ${e.code()}"
                )
            )
            e.printStackTrace()
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't reach the server\nCheck your internet connection"))
            e.printStackTrace()
        }
    }
}