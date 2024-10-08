package me.ashutoshkk.stonks.data.remote.dto

import me.ashutoshkk.stonks.domain.model.Stock

data class TopGainer(
    val change_amount: String,
    val change_percentage: String,
    val price: String,
    val ticker: String,
    val volume: String
)

fun TopGainer.toStock() = Stock(
    ticker = ticker,
    price = price,
    changePercentage = change_percentage,
    volume = volume,
    changeAmount = change_amount
)