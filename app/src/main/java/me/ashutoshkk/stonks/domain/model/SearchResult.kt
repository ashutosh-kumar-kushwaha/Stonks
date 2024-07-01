package me.ashutoshkk.stonks.domain.model

data class SearchResult(
    val symbol: String,
    val name: String,
    val type: Type,
)

enum class Type {
    Equity, ETF, MutualFund
}