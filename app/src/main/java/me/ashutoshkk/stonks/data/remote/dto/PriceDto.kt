package me.ashutoshkk.stonks.data.remote.dto

data class PriceDto(
    val open: Float,
    val high: Float,
    val low: Float,
    val close: Float,
    val volume: Long,
)
