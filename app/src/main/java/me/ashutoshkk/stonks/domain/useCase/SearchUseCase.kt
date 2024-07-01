package me.ashutoshkk.stonks.domain.useCase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.ashutoshkk.stonks.common.Resource
import me.ashutoshkk.stonks.data.remote.dto.toSearchResult
import me.ashutoshkk.stonks.data.room.SearchHistory
import me.ashutoshkk.stonks.domain.model.SearchResult
import me.ashutoshkk.stonks.domain.repository.SearchRepository
import retrofit2.HttpException
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val repository: SearchRepository) {

    operator fun invoke(query: String): Flow<Resource<List<SearchResult>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repository.search(query).bestMatches.map { it.toSearchResult() }))
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

    fun getSearchHistory(): Flow<List<SearchHistory>> = repository.getSearchHistory()

    suspend fun addToSearchHistory(history: SearchHistory) = repository.addToSearchHistory(history)

}