package me.ashutoshkk.stonks.domain.model

data class SearchResult(
    val symbol: String,
    val name: String,
    val type: FilterType,
)

enum class FilterType(val text: String) {
    Equity("Equity"),
    ETF("ETF"),
    MutualFund("Mutual Fund"),
    None("All")
}