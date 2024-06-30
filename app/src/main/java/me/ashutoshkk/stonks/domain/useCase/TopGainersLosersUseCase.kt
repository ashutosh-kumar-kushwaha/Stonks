package me.ashutoshkk.stonks.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.stonks.common.Resource
import me.ashutoshkk.stonks.data.remote.dto.toTopGainersLosers
import me.ashutoshkk.stonks.domain.model.TopGainersLosers
import me.ashutoshkk.stonks.domain.repository.HomeRepository
import retrofit2.HttpException
import javax.inject.Inject

class TopGainersLosersUseCase @Inject constructor(private val repository: HomeRepository) {

    operator fun invoke(): Flow<Resource<TopGainersLosers>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.getGainersLosers().toTopGainersLosers()))
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