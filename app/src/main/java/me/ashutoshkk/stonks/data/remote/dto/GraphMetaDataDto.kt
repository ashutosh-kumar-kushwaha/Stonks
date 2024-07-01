package me.ashutoshkk.stonks.data.remote.dto

data class GraphMetaDataDto(
    val information: String,
    val symbol: String,
    val lastRefreshed: String,
    val outputSize: String,
    val timeZone: String
)
