package me.ashutoshkk.stonks.data.remote.dto

data class PriceDto(
    val open: Double,
    val high: Double,
    val low: Double,
    val close: Double,
    val volume: Long,
)
