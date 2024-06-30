package me.ashutoshkk.stocksapp.domain.model

data class TopGainersLosers(
    val topGainers: List<Stock>,
    val topLosers: List<Stock>
)
