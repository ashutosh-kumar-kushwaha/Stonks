package me.ashutoshkk.stonks.domain.model

data class TopGainersLosers(
    val topGainers: List<Stock>,
    val topLosers: List<Stock>
)
