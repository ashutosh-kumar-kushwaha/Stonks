package me.ashutoshkk.stonks.data.remote.dto

import me.ashutoshkk.stonks.domain.model.TopGainersLosers

data class TopGainersLosersDto(
    val last_updated: String,
    val metadata: String,
    val most_actively_traded: List<MostActivelyTraded>,
    val top_gainers: List<TopGainer>,
    val top_losers: List<TopLoser>
)

fun TopGainersLosersDto.toTopGainersLosers() = TopGainersLosers(
    topGainers = top_gainers.map { it.toStock() },
    topLosers = top_losers.map { it.toStock() }
)