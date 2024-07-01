package me.ashutoshkk.stonks.domain.repository

import me.ashutoshkk.stonks.data.remote.dto.SearchDto

interface SearchRepository {

    suspend fun search(query: String): SearchDto

}