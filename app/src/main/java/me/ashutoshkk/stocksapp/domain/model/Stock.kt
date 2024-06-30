package me.ashutoshkk.stocksapp.domain.model

data class Stock(
    val changeAmount: String,
    val changePercentage: String,
    val price: String,
    val ticker: String,
    val volume: String
)
